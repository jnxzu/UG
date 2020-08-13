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

import J.AppUsers.model.User;

@Controller
@RequestMapping("/edituser")
public class EditUserController {
    @Autowired
    @Qualifier("userlist")
    private List<User> userlist;

    private List<String> tmpApps;
    private String tmpId;

    @GetMapping
    public String edituser(final Model model, String id) {
        for (User usr : userlist) {
            if (usr.getId().equals(id)) {
                tmpId=usr.getId();
                tmpApps=usr.getApps();
                userlist.remove(usr);
                model.addAttribute("user", usr);
                break;
            }
        }
        return "edituser";
    }

    @PostMapping
    public String processOrder(@Valid final User user, final Errors errors) {
        if (errors.hasErrors()) {
            return "edituser";
        }
        user.setId(tmpId);
        user.setApps(tmpApps);
        userlist.add(user);
        return "redirect:/users";
    }
}
