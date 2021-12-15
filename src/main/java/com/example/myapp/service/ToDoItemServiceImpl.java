package com.example.myapp.service;

import com.example.myapp.dto.TodoItemDto;
import com.example.myapp.entity.TodoItem;
import com.example.myapp.repository.TodoItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jiangtaotao
 */
@Service
public class ToDoItemServiceImpl implements ToDoItemService {

    private final TodoItemRepository todoEventRepository;


    public ToDoItemServiceImpl(TodoItemRepository todoItemRepository) {
        this.todoEventRepository = todoItemRepository;
    }

    @Override
    public TodoItemDto addTodoItem(TodoItemDto todoItemDto) {
        TodoItem save = todoEventRepository.save(todoItemDto.transferToTodoItem());
        return TodoItemDto.getTodoItemDto(save);
    }

    @Override
    public TodoItemDto updateTodoItemStatus(Long id, int status) {
        TodoItem todoItem = todoEventRepository.findById(id).get();
        todoItem.setStatus(status);
        TodoItem save = todoEventRepository.save(todoItem);
        return TodoItemDto.getTodoItemDto(save);
    }

    @Override
    public void deleteTodoItemById(long id) {
        todoEventRepository.deleteById(id);
    }

    @Override
    public List<TodoItemDto> getAllTodoItem() {
        List<TodoItem> allTodoItems = todoEventRepository.findAll();
        List<TodoItemDto> todoItemDtos = new ArrayList<>(0);
        if (!CollectionUtils.isEmpty(allTodoItems)){
            todoItemDtos = allTodoItems.stream().map(todoItem -> TodoItemDto.getTodoItemDto(todoItem)).collect(Collectors.toList());
        }
        return todoItemDtos;
    }
}
