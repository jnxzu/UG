package J.AppUsers.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import J.AppUsers.model.App;
import J.AppUsers.model.StatsResponse;
import J.AppUsers.model.User;

@Controller
public class StatsController {
    @Autowired
    @Qualifier("userlist")
    private List<User> userlist;
    @Autowired
    @Qualifier("applist")
    private List<App> applist;

    @RequestMapping("/stats")
    public String stats(final Model model) {
        model.addAttribute("apki", applist);
        return "stats";
    }

    @RequestMapping("/getappinfo")
    @ResponseBody
    public StatsResponse appInfo(@RequestParam("id") String id) {
        StatsResponse response = new StatsResponse();
        HashMap<String, Integer> countries = new HashMap<String, Integer>();
        for (App app : applist) {
            if (app.getId().equals(id)) {
                response.setNazwa(app.getName());
                for (User user : userlist) {
                    if (app.getUsers().contains(user.getId())) {
                        String kraj = user.getKraj();
                        int count = countries.containsKey(kraj) ? countries.get(kraj) : 0;
                        countries.put(kraj, count + 1);
                    }
                }
                break;
            }
        }
        Map<Object, Object> map = null;
        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();

        for (Map.Entry<String, Integer> entry : countries.entrySet()) {
            map = new HashMap<Object, Object>();
            map.put("label", entry.getKey());
            map.put("y", entry.getValue());
            list.add(map);
        }

        response.setKraje(list);
        return response;
    }
}
