package org.launchcode.cheesemvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    static List<String> cheeses = new LinkedList<>();

    @RequestMapping(value = "")
    public String index(Model model, HttpServletRequest request) {
        // give the template path (without file extension)
        String value = request.getParameter("q");
        if (value == null) value = "My Cheese";
        // insert attribute key(name)-value pairs into the model object
        // model passes the data from controller into the view
        model.addAttribute("title", value);
        model.addAttribute("cheeses", cheeses);
        // path is relative to templates folder inside resources folder
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm() {
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName) {
        // cheeseName variable contains the value of the RequestParam "cheeseName"
        cheeses.add(cheeseName);
        return "redirect:";
    }

}
