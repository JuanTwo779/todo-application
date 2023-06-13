package com.Juan.todoadpplication.services;

import com.Juan.todoadpplication.models.ToDoItem;
import com.Juan.todoadpplication.repository.ToDoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

/**
 *Services
 */

@Service
public class ToDoItemService {

    @Autowired
    private ToDoItemRepository toDoItemRepository;

    //lists all to do items
    public Iterable<ToDoItem> getAll() {
        return toDoItemRepository.findAll();
    }

    //gets single item by ID
    public Optional<ToDoItem> getById(Long id){
        return toDoItemRepository.findById(id);
    }

    //save a new item
    public ToDoItem save(ToDoItem toDoItem){
        if (toDoItem.getId() == null){
            toDoItem.setCreatedAt(Instant.now());
        }
        toDoItem.setUpdatedAt(Instant.now());
        return toDoItemRepository.save(toDoItem);
    }

    //delete method
    public void delete(ToDoItem toDoItem){
        toDoItemRepository.delete(toDoItem);
    }
}
