package uwr.onlinejudge.server.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uwr.onlinejudge.server.models.Group;
import uwr.onlinejudge.server.models.TaskDescription;
import uwr.onlinejudge.server.models.TaskList;
import uwr.onlinejudge.server.models.form.TaskDescriptionForm;
import uwr.onlinejudge.server.models.form.TaskListForm;
import uwr.onlinejudge.server.repositories.TaskDescriptionRepository;
import uwr.onlinejudge.server.repositories.TaskListRepository;

import java.util.Collection;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskListRepository taskListRepository;
    private ObjectMapper objectMapper;
    private TaskDescriptionRepository taskDescriptionRepository;

    @Autowired
    public TaskServiceImpl(TaskListRepository taskListRepository, ObjectMapper objectMapper, TaskDescriptionRepository taskDescriptionRepository) {
        this.taskListRepository = taskListRepository;
        this.objectMapper = objectMapper;
        this.taskDescriptionRepository = taskDescriptionRepository;
    }

    @Override
    public Collection<TaskList> getTaskLists(Group group) {
        return taskListRepository.findByGroup(group);
    }

    @Override
    public void save(TaskListForm taskListForm) {
        taskListRepository.save(objectMapper.convertValue(taskListForm, TaskList.class));
    }

    @Override
    public void save(TaskDescriptionForm taskDescriptionForm) {
        taskDescriptionRepository.save(objectMapper.convertValue(taskDescriptionForm, TaskDescription.class));
    }

    @Override
    public TaskDescription getTaskDescription(Long id) {
        return taskDescriptionRepository.findOne(id);
    }

    @Override
    public TaskList getTaskList(Long id) {
        return taskListRepository.findOne(id);
    }


}
