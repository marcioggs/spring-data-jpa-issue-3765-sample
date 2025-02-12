package com.example.demo.repositories;

import com.example.demo.entities.TaskItem;
import com.example.demo.entities.TaskList;
import jakarta.persistence.criteria.Join;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TaskListRepositoryTest {

  @Autowired private TaskListRepository taskListRepository;
  @Autowired private TaskItemRepository taskItemRepository;

  @Test
  void test() {

    // given
    saveTaskListAndItem(1L, "Work", 10L, "Report issue");
    Specification<TaskList> specification = itemHasDescription("Report issue");

    // when
    Page<TaskList> taskLists = taskListRepository.findAll(specification, Pageable.ofSize(1));

    // then
    assertThat(taskLists).isNotEmpty();
  }

  private void saveTaskListAndItem(
      Long taskListId, String taskListDescription, Long taskItemId, String taskItemDescription) {

    TaskList taskList = saveTaskList(taskListId, taskListDescription);
    saveTaskItem(taskItemId, taskItemDescription, taskList);
  }

  private TaskList saveTaskList(Long taskListId, String taskListDescription) {

    TaskList taskList = new TaskList();
    taskList.setId(taskListId);
    taskList.setDescription(taskListDescription);

    taskListRepository.save(taskList);

    return taskList;
  }

  private void saveTaskItem(Long taskItemId, String taskItemDescription, TaskList taskList) {

    TaskItem taskItem = new TaskItem();
    taskItem.setId(taskItemId);
    taskItem.setDescription(taskItemDescription);
    taskItem.setTaskList(taskList);

    taskItemRepository.save(taskItem);
  }

  private Specification<TaskList> itemHasDescription(String description) {
    return (root, query, builder) -> {
      Join<TaskList, TaskItem> join =
          (Join<TaskList, TaskItem>) root.<TaskList, TaskItem>fetch("taskItems");
      return builder.equal(join.get("description"), description);
    };
  }
}
