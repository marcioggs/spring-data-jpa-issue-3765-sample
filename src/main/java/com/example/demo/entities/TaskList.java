package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class TaskList {

  @Id
  @Column(nullable = false)
  private Long id;

  @Column private String description;

  @OneToMany(mappedBy = "taskList")
  private List<TaskItem> taskItems;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<TaskItem> getTaskItems() {
    return taskItems;
  }

  public void setTaskItems(List<TaskItem> taskItems) {
    this.taskItems = taskItems;
  }
}
