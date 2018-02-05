package net.shop.controller;

import net.shop.model.Sms;
import net.shop.model.region.Country;
import net.shop.service.RegionService;
import net.shop.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Controller
//@RequestMapping("/sms")
public class SmsController {
    private SmsService smsService;

    @Autowired(required = true)
    @Qualifier(value = "smsService")
    public void setSmsService(SmsService smsService) {
        this.smsService = smsService;
    }


    @RequestMapping(value = "addsms", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public HashMap<String, String> addSms(@Valid @ModelAttribute("sms") Sms sms, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors())
            return null;
        return smsService.addCode(request.getRemoteAddr(), sms);
    }


}