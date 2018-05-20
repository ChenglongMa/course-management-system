package team.high5.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import team.high5.domain.entities.Course;
import team.high5.domain.entities.CourseOffering;
import team.high5.domain.entities.Schedule;
import team.high5.service.AdminService;
import team.high5.service.CourseOfferingService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * course-management-system
 *
 * @Author : Charles Ma
 * @Date : 19-05-2018
 * @Time : 19:59
 * @Description :
 */
@Controller
@RequestMapping("/offering")
public class OfferingController {
    private final AdminService adminService;
    private final CourseOfferingService offeringService;

    @Autowired
    public OfferingController(AdminService adminService, CourseOfferingService offeringService) {
        this.adminService = adminService;
        this.offeringService = offeringService;

    }

    @GetMapping
    public ModelAndView initial(String kwd) {
        ModelAndView mav = new ModelAndView("offering");
        List<CourseOffering> offerings = offeringService.findAll();
        if (offerings != null && !offerings.isEmpty()) {
            offerings.sort((o1, o2) -> {
                if (o1.getCourse() == null || o1.getCourse().getCode() == null) {
                    return -1;
                }
                return o1.getCourse().getCode().compareTo(o2.getCourse().getCode());
            });
        } else offerings = new ArrayList<>();
        mav.addObject("offers", offerings);
        mav.addObject("currSch", Schedule.currentSchedule());
        mav.addObject("offering", new CourseOffering(new Course()));
        mav.addObject("action", "create");
        mav.addObject("errorMsg", "");
        return mav;
    }

    @GetMapping(value = "/create")
    public String createNewOffering(ModelMap map) {
        map.addAttribute("offering", new CourseOffering());
        map.addAttribute("action", "create");
        return "offering";
    }

    @PostMapping(value = "/create")
    public Map<String, String> postOffering(@ModelAttribute CourseOffering offering) {
        Map<String, String> map = new HashMap<>();
        try {
            adminService.addCourseOffering(offering);
            map.put("href", "redirect:/offering");
        } catch (Exception e) {
            map.put("errorMsg", e.getMessage());
        }
        return map;
    }

}
