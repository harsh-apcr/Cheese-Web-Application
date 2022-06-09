package org.launchcode.cheesemvc.controller;

import org.launchcode.cheesemvc.models.Category;
import org.launchcode.cheesemvc.models.data.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "category")
public class CategoryController {

    @Autowired
    private CategoryDAO categoryDAO;

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", "Category");
        model.addAttribute("categories", categoryDAO.findAll());
        return "category/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCategoryForm(Model model) {
        model.addAttribute("title" , "Add Category");
        model.addAttribute(new Category());
        return "category/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCategoryForm(@ModelAttribute @Valid Category cat,
                                       Errors errors,
                                       Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title" , "Add Category");
            return "category/add";
        }
        categoryDAO.save(cat);
        return "redirect:";

    }


    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCategoryForm(Model model) {
        model.addAttribute("title", "Remove Category");
        model.addAttribute("categories", categoryDAO.findAll());
        return "category/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCategoryForm(@RequestParam int[] catIds) {
        for(int id : catIds) {
            categoryDAO.deleteById(id);
        }
        return "redirect:";
    }

}
