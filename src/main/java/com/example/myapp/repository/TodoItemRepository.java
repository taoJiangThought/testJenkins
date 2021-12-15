package com.example.myapp.repository;

import com.example.myapp.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author jiangtaotao
 */
@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem,Long> {
}
