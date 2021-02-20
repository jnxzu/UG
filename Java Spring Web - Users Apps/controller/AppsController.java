package J.AppUsers.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import J.AppUsers.model.AdminState;
import J.AppUsers.model.App;
import J.AppUsers.model.User;

@Controller
public class AppsController {
    @Autowired
    @Qualifier("userlist")
    private List<User> userlist;
    @Autowired
    @Qualifier("applist")
    private List<App> applist;
    @Autowired
    @Qualifier("admin")
    private AdminState admin;

    @RequestMapping("/apps")
    public String apps(final Model model) {
        model.addAttribute("apki", applist);
        return "apps";
    }

    @RequestMapping(value = "/checkadminapps", method = RequestMethod.POST)
    @ResponseBody
    public AdminState checkAdminApps() {
        return admin;
    }

    @RequestMapping(value = "/loadappdetails", method = RequestMethod.POST)
    @ResponseBody
    public App getAppData(@RequestParam String id) {
        App result = null;
        for (App app : applist) {
            if (app.getId().equals(id)) {
                result = app;
                break;
            }
        }
        return result;
    }

    @RequestMapping(value = "/loadappusers", method = RequestMethod.POST, params = "userzy[]")
    @ResponseBody
    public List<User> getUserApps(@RequestParam(value = "userzy[]") String[] userIds) {
        List<User> appsusers = new ArrayList<User>();
        for (String id : userIds) {
            for (User usr : userlist) {
                if (usr.getId().equals(id)) {
                    appsusers.add(usr);
                    break;
                }
            }
        }
        return appsusers;
    }

    @RequestMapping(value = "/loadappusers", method = RequestMethod.POST)
    @ResponseBody
    public List<User> getUserApps() {
        List<User> appsusers = new ArrayList<User>();
        return appsusers;
    }

    @RequestMapping(value = "/loadavailableusers", method = RequestMethod.POST, params = "userzy[]")
    @ResponseBody
    public List<User> getAvailableUsers(@RequestParam(value = "userzy[]") String[] userIds) {
        List<User> availableusers = new ArrayList<User>();
        boolean doesntUseApp = false;
        for (User user : userlist) {
            doesntUseApp = true;
            for (String userId : userIds) {
                if (user.getId().equals(userId)) {
                    doesntUseApp = false;
                    break;
                }
            }
            if (doesntUseApp)
                availableusers.add(user);
        }
        return availableusers;
    }

    @RequestMapping(value = "/loadavailableusers", method = RequestMethod.POST)
    @ResponseBody
    public List<User> getAvailableUsers() {
        return userlist;
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void addUser(@RequestParam("userId") String userId, @RequestParam("appId") String appId) {
        for (App app : applist) {
            if (app.getId().equals(appId)) {
                for (User user : userlist) {
                    if (user.getId().equals(userId)) {
                        user.getApps().add(app.getId());
                        app.getUsers().add(user.getId());
                        break;
                    }
                }
                break;
            }
        }
    }

    @RequestMapping(value = "/removeuser", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void removeUser(@RequestParam("userId") String userId, @RequestParam("appId") String appId) {
        for (App app : applist) {
            if (app.getId().equals(appId)) {
                for (User user : userlist) {
                    if (user.getId().equals(userId)) {
                        user.getApps().remove(app.getId());
                        app.getUsers().remove(user.getId());
                        break;
                    }
                }
                break;
            }
        }
    }

    @RequestMapping(value = "/removeapp", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void removeApp(@RequestParam("id") String appId) {
        for (App app : applist) {
            if (app.getId().equals(appId)) {
                applist.remove(app);
                break;
            }
        }
        for (User usr : userlist) {
            if (usr.getApps().contains(appId))
                usr.getApps().remove(appId);
        }
    }
}
