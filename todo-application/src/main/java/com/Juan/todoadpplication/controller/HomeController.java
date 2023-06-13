package com.Juan.todoadpplication.controller;

import com.Juan.todoadpplication.services.ToDoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private ToDoItemService toDoItemService;

    @GetMapping("/")
    public ModelAndView index() {
        //looks for index file in templates
        ModelAndView modelAndView = new ModelAndView("index");

        //pass to do items from service and dumping them into Model and view object
        //giving it key name toDoItems
        //Spring makes it available to user's view
        modelAndView.addObject("toDoItems", toDoItemService.getAll());
        return modelAndView;
    }
}
