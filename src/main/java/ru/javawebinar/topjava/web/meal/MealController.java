package ru.javawebinar.topjava.web.meal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;


/**
 * Created by Maks on 09.05.2017.
 */
@Controller
@RequestMapping(value = "/meals")
public class MealController extends AbstractUserMealController {
    @RequestMapping(method = RequestMethod.GET)
    public String mealList(Model model) {
        model.addAttribute("meals", super.getAll());
        return "meals";
    }

    @RequestMapping(params = {"action=create"}, method = RequestMethod.GET)
    public String mealCreate(@RequestParam("action") String action, HttpServletRequest request) {
        final Meal meal = new Meal(LocalDateTime.now(), "", 1000);
        request.setAttribute("meal", meal);
        return "meal";
    }


    @RequestMapping(params = {"action=update"}, method = RequestMethod.GET)
    public String mealUpdate(@RequestParam("action") String action, HttpServletRequest request) {
        final Meal meal = super.get(getId(request));
        request.setAttribute("meal", meal);
        return "meal";
    }

    @RequestMapping(params = {"action=delete"}, method = RequestMethod.GET)
    public String mealDelete(@RequestParam("action") String action, HttpServletRequest request) {
        int id = getId(request);
        super.delete(id);
        return "redirect:meals";
    }

    @RequestMapping(params = {"action=save"}, method = RequestMethod.POST)
    public String mealSave(@RequestParam("action") String action, HttpServletRequest request) throws UnsupportedEncodingException {

        final Meal userMeal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));

        if (request.getParameter("id").isEmpty()) {
            super.create(userMeal);
        } else {
            super.update(userMeal, getId(request));
        }
        return "redirect:meals";

    }
    @RequestMapping(params = {"action=filter"}, method = RequestMethod.POST)
    public String mealFilter(@RequestParam("action") String action, HttpServletRequest request) throws UnsupportedEncodingException {

        LocalDate startDate = DateTimeUtil.parseLocalDate(resetParam("startDate", request));
        LocalDate endDate = DateTimeUtil.parseLocalDate(resetParam("endDate", request));
        LocalTime startTime = DateTimeUtil.parseLocalTime(resetParam("startTime", request));
        LocalTime endTime = DateTimeUtil.parseLocalTime(resetParam("endTime", request));
        request.setAttribute("meals", super.getBetween(startDate, startTime, endDate, endTime));

        return "meals";
    }


    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"), "parameter id  must not be null");
        return Integer.valueOf(paramId);
    }

    private String resetParam(String param, HttpServletRequest request) {
        String value = request.getParameter(param);
        request.setAttribute(param, value);
        return value;
    }
}
