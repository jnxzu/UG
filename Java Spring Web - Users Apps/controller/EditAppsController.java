package J.AppUsers.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import J.AppUsers.model.App;

@Controller
@RequestMapping("/editapp")
public class EditAppsController {
    @Autowired
    @Qualifier("applist")
    private List<App> applist;

    private List<String> tmpUsers;
    private String tmpId;

    @GetMapping
    public String editapp(final Model model, String id) {
        for (App app : applist) {
            if (app.getId().equals(id)) {
                tmpId=app.getId();
                tmpUsers=app.getUsers();
                applist.remove(app);
                model.addAttribute("app", app);
                break;
            }
        }
        return "editapp";
    }

    @PostMapping
    public String processOrder(@Valid final App app, final Errors errors) {
        if (errors.hasErrors()) {
            return "editapp";
        }
        app.setId(tmpId);
        app.setUsers(tmpUsers);
        applist.add(app);
        return "redirect:/apps";
    }
}
