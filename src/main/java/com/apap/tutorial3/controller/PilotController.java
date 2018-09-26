package com.apap.tutorial3.controller;

import com.apap.tutorial3.model.PilotModel;
import com.apap.tutorial3.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class PilotController {
    @Autowired
    private PilotService pilotService;

    @RequestMapping("/pilot/add")
    public String add (@RequestParam(value = "id") String id,
                       @RequestParam(value = "licenseNumber") String licenseNumber,
                       @RequestParam(value = "name") String name,
                       @RequestParam(value = "flyHour") int flyHour)
    {
        PilotModel pilot = new PilotModel(id, licenseNumber, name, flyHour);
        pilotService.addPilot(pilot);
        return "add";
    }

    @RequestMapping("/pilot/view")
    public String view (@RequestParam("licenseNumber") String licenseNumber, Model model) {
        PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);

        model.addAttribute("pilot", archive);
        return "view-pilot";
    }

    @RequestMapping("/pilot/viewall")
    public String viewall(Model model){
        List<PilotModel> archive = pilotService.getPilotList();

        model.addAttribute("listPilot", archive);
        return "viewall-pilot";
    }

    @RequestMapping("/pilot/view/license-number/{licenseNumber}")
    public String viewPilot(
            @PathVariable String licenseNumber,
            Model model)
    {
        PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);

        model.addAttribute("pilot", archive);
        return "view-pilot";
    }

    @RequestMapping("/pilot/update/license-number/{licenseNumber}/fly-hour/{newFlyHour}")
    public String updateFlyHour(
            @PathVariable String licenseNumber,
            @PathVariable int newFlyHour,
            Model model)
    {
        PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);

        if (archive != null){
            archive.setFlyHour(newFlyHour);
            model.addAttribute("success", true);
        }
        else {
            model.addAttribute("success", false);
        }
        return "update";
    }

    @RequestMapping("/pilot/delete/id/{pilotId}")
    public String deletePilot(
            @PathVariable String pilotId,
            Model model)
    {
        if (pilotService.deletePilot(pilotId)){
            model.addAttribute("success", true);
        }
        else {
            model.addAttribute("success", false);
        }
        return "delete";
    }
}
