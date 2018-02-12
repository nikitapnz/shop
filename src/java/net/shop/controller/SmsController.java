package net.shop.controller;

import net.shop.model.Sms;
import net.shop.model.User;
import net.shop.model.region.Country;
import net.shop.service.RegionService;
import net.shop.service.SmsService;
import net.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@Controller
//@RequestMapping("/sms")
public class SmsController {
    private SmsService smsService;

    @Autowired
    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "smsService")
    public void setSmsService(SmsService smsService) {
        this.smsService = smsService;
    }


    @RequestMapping(value = "addsms", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, String> addSms(@Valid @ModelAttribute("sms") Sms sms,
                                          BindingResult result,
                                          HttpServletRequest request,
                                          Principal principal) {
        if (result.hasErrors())
            return null;
        User user = userService.findByUsername(principal.getName());
        return smsService.addCode(request.getRemoteAddr(), sms, user);
    }

    @RequestMapping(value = "checkcode", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, String> checkCode(@Valid @ModelAttribute("sms") Sms sms,
                                             BindingResult result,
                                             HttpServletRequest request,
                                             Principal principal) {
        if (result.hasErrors())
            return null;
        User user = userService.findByUsername(principal.getName());
        return smsService.checkCode(request.getRemoteAddr(), sms, user);
    }

}