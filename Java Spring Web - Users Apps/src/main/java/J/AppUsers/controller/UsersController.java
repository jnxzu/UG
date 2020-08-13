package J.AppUsers.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import J.AppUsers.model.AdminState;
import J.AppUsers.model.App;
import J.AppUsers.model.User;

@Controller
public class UsersController {
    @Autowired
    @Qualifier("userlist")
    private List<User> userlist;
    @Autowired
    @Qualifier("applist")
    private List<App> applist;
    @Autowired
    @Qualifier("admin")
    private AdminState admin;

    @RequestMapping("/users")
    public String users(final Model model) {
        model.addAttribute("users", userlist);
        return "users";
    }

    @RequestMapping(value = "/checkadminusers", method = RequestMethod.POST)
    @ResponseBody
    public AdminState checkAdminUsers() {
        return admin;
    }

    @RequestMapping(value = "/loaduserdetails", method = RequestMethod.POST)
    @ResponseBody
    public User getUserData(@RequestParam String id) {
        User result = null;
        for (User usr : userlist) {
            if (usr.getId().equals(id)) {
                result = usr;
                break;
            }
        }
        return result;
    }

    @RequestMapping(value = "/loaduserapps", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<App> getUserApps(@RequestParam(value = "apki[]") String[] appIds, final Model model) {
        ArrayList<App> usersapps = new ArrayList<App>();
        for (String id : appIds) {
            for (App app : applist) {
                if (app.getId().equals(id)) {
                    usersapps.add(app);
                    break;
                }
            }
        }
        return usersapps;
    }
}
