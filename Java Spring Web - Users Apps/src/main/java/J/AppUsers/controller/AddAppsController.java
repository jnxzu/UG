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
@RequestMapping("/addapp")
public class AddAppsController {
    @Autowired
    @Qualifier("applist")
    private List<App> applist;

    @GetMapping
    public String addapp(final Model model) {
        model.addAttribute("app", new App());
        return "addapp";
    }

    @PostMapping
    public String processOrder(@Valid final App app, final Errors errors) {
        if (errors.hasErrors()) {
            return "addapp";
        }
        applist.add(app);
        return "redirect:/apps";
    }
}
