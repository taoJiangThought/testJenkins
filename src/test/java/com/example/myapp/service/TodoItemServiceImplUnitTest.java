package com.example.myapp.service;

import com.example.myapp.dto.TodoItemDto;
import com.example.myapp.entity.TodoItem;
import com.example.myapp.repository.TodoItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


class TodoItemServiceImplUnitTest {

    TodoItemRepository todoItemRepositoryMock = mock(TodoItemRepository.class);

    ToDoItemServiceImpl toDoItemService;

    @Test
    void givenValidTodoItem_whenSaveTodoItem_thenSucceed() {
        toDoItemService = new ToDoItemServiceImpl(todoItemRepositoryMock);
        TodoItemDto todoItemDto = new TodoItemDto();
        todoItemDto.setEventName("test_a");
        todoItemDto.setStatus(1);
        when(todoItemRepositoryMock.save(ArgumentMatchers.argThat(todoItemArg -> todoItemArg.getStatus() <= 1))).thenReturn(todoItemDto.transferToTodoItem());

        TodoItemDto todoItemDtoRep = toDoItemService.addTodoItem(todoItemDto);

        Assertions.assertEquals(todoItemDtoRep.getStatus(), todoItemDto.getStatus());
        Assertions.assertEquals(todoItemDtoRep.getEventName(), todoItemDto.getEventName());
    }


    @Test
    void givenValidTodoItem_whenUpdateTodoItem_thenSucceed() {
        toDoItemService = new ToDoItemServiceImpl(todoItemRepositoryMock);
        TodoItemDto todoItemDto = new TodoItemDto();
        todoItemDto.setEventName("test_a");
        todoItemDto.setStatus(0);
        todoItemDto.setId(1l);

        when(todoItemRepositoryMock.findById(ArgumentMatchers.eq(todoItemDto.getId()))).thenReturn(java.util.Optional.ofNullable(todoItemDto.transferToTodoItem()));
        when(todoItemRepositoryMock.save(ArgumentMatchers.argThat(todoItemArg -> todoItemArg.getStatus() <= 1))).thenReturn(todoItemDto.transferToTodoItem());

        TodoItemDto todoItemDtoRep = toDoItemService.updateTodoItemStatus(1l, 1);

        Assertions.assertEquals(todoItemDtoRep.getStatus(), todoItemDto.getStatus());
        Assertions.assertEquals(todoItemDtoRep.getEventName(), todoItemDto.getEventName());
    }

    @Test
    void givenValidTodoItemId_whenDeleteTodoItem_thenSucceed() {
        toDoItemService = new ToDoItemServiceImpl(todoItemRepositoryMock);

        toDoItemService.deleteTodoItemById(1l);

        verify(todoItemRepositoryMock).deleteById(1l);
    }

    @Test
    void whenGetAllTodoItems_thenSucceed() {
        toDoItemService = new ToDoItemServiceImpl(todoItemRepositoryMock);
        List<TodoItem> events = new ArrayList<>(0);
        TodoItem todoItem = new TodoItem();
        todoItem.setEventName("test_a");
        todoItem.setStatus(0);
        todoItem.setId(1l);
        events.add(todoItem);

        when(todoItemRepositoryMock.findAll()).thenReturn(events);

        List<TodoItemDto> allTodoEvent = toDoItemService.getAllTodoItem();

        Assertions.assertEquals(events.get(0).getId(), allTodoEvent.get(0).getId());
    }
}