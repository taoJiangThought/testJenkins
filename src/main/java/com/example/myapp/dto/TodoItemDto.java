package com.example.myapp.dto;

import com.example.myapp.entity.TodoItem;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author jiangtaotao
 */
public class TodoItemDto {

    private Long id;

    @NotEmpty
    private String eventName;

    /** status 为0 表示 未完成  1 表示完成
     */
    @Max(value = 1)
    @NotNull
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public  TodoItem transferToTodoItem(){
        TodoItem todoItem = new TodoItem();
        todoItem.setId(this.id);
        todoItem.setEventName(this.eventName);
        todoItem.setStatus(this.status);
        return todoItem;
    }

    public static TodoItemDto getTodoItemDto(TodoItem todoItem){
        TodoItemDto todoItemDto = new TodoItemDto();
        todoItemDto.setId(todoItem.getId());
        todoItemDto.setEventName(todoItem.getEventName());
        todoItemDto.setStatus(todoItem.getStatus());
        return todoItemDto;
    }

    @Override
    public String toString() {
        return "ToDoEvent{" +
                "eventName='" + eventName + '\'' +
                ", status=" + status +
                '}';
    }
}
