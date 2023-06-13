package com.Juan.todoadpplication.controller;

import com.Juan.todoadpplication.models.ToDoItem;
import com.Juan.todoadpplication.repository.ToDoItemRepository;
import com.Juan.todoadpplication.services.ToDoItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ToDoFormController {

    @Autowired
    private ToDoItemService toDoItemService;
    @Autowired
    private ToDoItemRepository toDoItemRepository;

    @GetMapping("/create-todo")
    public String showCreateForm(ToDoItem toDoItem){
        return "new-todo-item";
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid ToDoItem toDoItem, BindingResult result, Model model) {

        //fields come from form passed in via Spring
        //new todoitem is created and saved to DB
        ToDoItem item = new ToDoItem();
        item.setDescription(toDoItem.getDescription());
        item.setIsComplete(toDoItem.getIsComplete());
        toDoItemService.save(toDoItem);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteToDoItem(@PathVariable("id") Long id, Model model){
        //Looks through to do service for the given item
        ToDoItem toDoItem = toDoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("ToDoItem id: " + id + " not found"));

        toDoItemService.delete(toDoItem);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        //Looks through to do service for the given item
        ToDoItem toDoItem = toDoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("ToDoItem id: " + id + " not found"));

        model.addAttribute("todo", toDoItem);
        return "edit-todo-item";
    }

    @PostMapping("/todo/{id}")
    public String updateToDoItem(@PathVariable("id") Long id, @Valid ToDoItem toDoItem, BindingResult result, Model model) {
        //Gets the to do item from service
        ToDoItem item = toDoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("ToDoItem id: " + id + " not found"));

        item.setIsComplete(toDoItem.getIsComplete());
        item.setDescription(toDoItem.getDescription());

        toDoItemService.save(item);

        return "redirect:/";
    }

}
