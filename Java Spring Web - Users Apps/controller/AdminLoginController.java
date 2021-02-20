package J.AppUsers.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import J.AppUsers.model.AdminState;
import J.AppUsers.model.AdminValidation;

@Controller
@RequestMapping("/adminlogin")
public class AdminLoginController {
    @Autowired
    @Qualifier("admin")
    private AdminState admin;

    @GetMapping
    public String adminlogin(final Model model) {
        model.addAttribute("adminvalidation", new AdminValidation());
        return "adminlogin";
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute("adminvalidation") final AdminValidation adminvalidation, final Errors errors) {
        if (errors.hasErrors()) {
            return "adminlogin";
        }
        admin.setState(true);
        return "redirect:/";
    }
}
