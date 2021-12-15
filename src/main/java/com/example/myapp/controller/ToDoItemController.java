package com.example.myapp.controller;

import com.example.myapp.dto.TodoItemDto;
import com.example.myapp.entity.TodoItem;
import com.example.myapp.service.ToDoItemService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.List;

/**
 * @author jiangtaotao
 */
@RestController
@RequestMapping("/todoItems")
@Validated
public class ToDoItemController {

    private final ToDoItemService toDoItemService;

    public ToDoItemController(ToDoItemService toDoItemService) {
        this.toDoItemService = toDoItemService;
    }

    /**
     *  create todoItems
     */
     @PostMapping
    public TodoItemDto addTodoItem(@RequestBody @Valid TodoItemDto todoItemDto){
         Long id = todoItemDto.getId();
         if (id != null){
             throw  new IllegalArgumentException("creating todoItem,the id should be null ");
         }
         return toDoItemService.addTodoItem(todoItemDto);
     }


    /**
     * -  update todoItem to completed status
     */
    @PutMapping
    public TodoItemDto updateTodoItemStatus(@RequestParam(value = "id") Long id , @RequestParam("status") @Valid @Max(1) Integer status){
       return toDoItemService.updateTodoItemStatus(id, status);
    }

    /**
     * - delete todoItem
     */
    @DeleteMapping("/{id}")
    public void deleteTodoItemById(@PathVariable long id){
       toDoItemService.deleteTodoItemById(id);
    }

    /**
     *
     *  get all todoItems
     */
    @GetMapping
    public List<TodoItemDto> getAllTodoItem(){
        List<TodoItemDto> allTodoItem = toDoItemService.getAllTodoItem();
        return allTodoItem;
    }
}
