package net.shop.controller;

import net.shop.model.region.City;
import net.shop.model.region.Country;
import net.shop.model.region.Region;
import net.shop.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/region")
public class RegionController {
    private RegionService regionService;

    @Autowired(required = true)
    @Qualifier(value = "regionService")
    public void setRegionService(RegionService regionService) {
        this.regionService = regionService;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String regionTest(Model model) {
        List<Country> list = regionService.getAllCountries();
        model.addAttribute("regionTest", list);
        // model.addAttribute("regionTest2", regionService.getRegionsFromCountry(list.get(0)));
        //  System.out.println(regionService.getCitiesFromRegion(1).get(0).getUsers());
        return "test";
    }


    @RequestMapping(value = "countries", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Country> getCountries() {
        List<Country> list = regionService.getAllCountries();
        return list;
    }

    @RequestMapping(value = "regions/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Region> getRegions(@PathVariable("id") int id) {
        List<Region> list = regionService.getRegionsFromCountry(id);
        return list;
    }

    @RequestMapping(value = "cities/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<City> getCities(@PathVariable("id") int id) {
        List<City> list = regionService.getCitiesFromRegion(id);
        return list;
    }

}