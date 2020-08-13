package J.AppUsers.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import J.AppUsers.model.AdminState;
import J.AppUsers.model.AdminValidation;
import J.AppUsers.model.App;
import J.AppUsers.model.User;

@Controller
public class HomeController {
    @Autowired
    List<User> lUsers;
    @Autowired
    List<App> lApps;
    @Autowired
    @Qualifier("userlist")
    private List<User> userlist;
    @Autowired
    @Qualifier("applist")
    private List<App> applist;
    @Autowired
    @Qualifier("admin")
    private AdminState admin;

    @RequestMapping("/")
    public String home(final Model model) {
        if (applist.isEmpty()) {
            for (App app : lApps) {
                applist.add(app);
            }
        }
        if (userlist.isEmpty()) {
            for (User usr : lUsers) {
                userlist.add(usr);
            }
            this.AssignUsers();
        }
        model.addAttribute("adminvalidation", new AdminValidation());
        return "home";
    }

    @RequestMapping(value = "/checkadmin", method = RequestMethod.POST)
    @ResponseBody
    public AdminState checkAdmin() {
        return admin;
    }

    @RequestMapping(value = "/adminlogout", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void adminLogout() {
        admin.setState(false);
    }

    private void AssignUsers() {
        int userCount = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < userlist.size(); i++) {
            list.add(new Integer(i));
        }
        for (App app : applist) {
            if (app.getUsers().isEmpty()) {
                userCount = ThreadLocalRandom.current().nextInt(1, userlist.size() / 2);
                Collections.shuffle(list);
                for (int i = 0; i < userCount; i++) {
                    int index = list.get(i);
                    User user = userlist.get(index);
                    if (!user.getApps().contains(app.getId())) {
                        user.getApps().add(app.getId());
                        app.getUsers().add(user.getId());
                    }
                }
            }
        }
    }
}
