package net.shop.controller;

import net.shop.model.Sms;
import net.shop.model.User;
//import net.shop.security.SecurityService;
import net.shop.security.SecurityService;
import net.shop.service.SmsService;
import net.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;


@Controller
public class UserController {
    private UserService userService;
    private SmsService smsService;

    @Autowired
    private SecurityService securityService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired(required = true)
    @Qualifier(value = "smsService")
    public void setSmsService(SmsService smsService) {
        this.smsService = smsService;
    }

    @RequestMapping(value = "welcome", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", this.userService.listUsers());

        return "users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        if (user.getId() == 0) {
            // this.userService.addUser(user);
        } else {
            this.userService.updateUser(user);
        }

        return "redirect:/users";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id) {
        this.userService.removeUser(id);

        return "redirect:/users";
    }

    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("listUsers", this.userService.listUsers());

        return "users";
    }

    @RequestMapping("userdata/{id}")
    public String userData(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));

        return "login";
    }

    //////////////////// New functional///////////////

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute("user") User user,
                               BindingResult result,
                               Model model,
                               HttpServletRequest request) {
        if (result.hasErrors())
            return "test";
        if (user.getId() == 0)
            if (userService.addUser(user, result)) {
                securityService.autoLogin(user.getUsername(), user.getPassword());
                return "redirect:/verification";
            }
            user.setPassword("");
        return "test";
    }

    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "test";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout, HttpServletRequest request) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("error", "Logout was");
        }
        return "login";
    }

    @RequestMapping(value = "/verification", method = RequestMethod.GET)
    public String verification(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        if (user.getPhone() == null)
            model.addAttribute("phone", new Sms());
        else
            model.addAttribute("newPhone", user.getPhone());
//        if (user.getEmail() == null)
//            model.addAttribute("email", );
        return "verification";
    }

}