package com.example.myapp.entity;

import javax.persistence.*;

/**
 * @author jiangtao
 */
@Entity
@Table(name="todoEvent")
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "eventName",nullable = false)
    private String eventName;

    /** status 0 : undo  1 : finished
     */
    @Column(name = "status",nullable = false)
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

    @Override
    public String toString() {
        return "ToDoEvent{" +
                "eventName='" + eventName + '\'' +
                ", status=" + status +
                '}';
    }
}
