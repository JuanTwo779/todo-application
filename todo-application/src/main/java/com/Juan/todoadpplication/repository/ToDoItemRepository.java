package com.Juan.todoadpplication.repository;

import com.Juan.todoadpplication.models.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository
 */

@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {
}
