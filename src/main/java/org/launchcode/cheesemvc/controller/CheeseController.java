package org.launchcode.cheesemvc.controller;

import org.launchcode.cheesemvc.models.data.CategoryDAO;
import org.launchcode.cheesemvc.models.data.CheeseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.launchcode.cheesemvc.models.Cheese;

@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    @Autowired  // framework gives an instance of this interface automatically
    private CheeseDAO cheeseDAO;
    @Autowired
    private CategoryDAO categoryDAO;

    @RequestMapping(value = "")
    public String index(Model model, HttpServletRequest request) {
        // give the template path (without file extension)
        String value = request.getParameter("q");
        if (value == null) value = "My Cheese";
        // insert attribute key(name)-value pairs into the model object
        // model passes the data from controller into the view
        model.addAttribute("title", value);
        model.addAttribute("cheeses", cheeseDAO.findAll());
        // path is relative to templates folder inside resources folder
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute("cheeseCats", categoryDAO.findAll());
        model.addAttribute(new Cheese());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese,
                                       Errors errors,
                                       Model model) {
        // cheeseName variable contains the value of the RequestParam "cheeseName"
        /*
        * Cheese newCheese = new Cheese();
        * newCheese.name = request.getParameter("name");
        * newCheese.description = request.getParameter("description");
        * */
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            return "cheese/add";
        }
        cheeseDAO.save(newCheese);
        return "redirect:";         // redirect means -> it's spring is going to redirect to index method
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheeses", cheeseDAO.findAll());
        return "cheese/remove";
    }
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {
        for(int id : cheeseIds) {
            cheeseDAO.deleteById(id);
        }
        return "redirect:";
    }


}
