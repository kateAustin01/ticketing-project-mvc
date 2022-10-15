package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {
    private final TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }
    @Override
    public ProjectDTO save(ProjectDTO object) {
        if(object.getProjectStatus()==null){
            object.setProjectStatus(Status.OPEN);
        }
        return super.save(object.getProjectCode(), object);
    }


    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(ProjectDTO object) {

        ProjectDTO projectDTO = findById(object.getProjectCode());
        if(object.getProjectStatus()==null){
            object.setProjectStatus(projectDTO.getProjectStatus());
        }
        super.update(object.getProjectCode(),object);
    }

    @Override
    public void deleteById(String id) {
        super.deleteById(id);
    }

    @Override
    public ProjectDTO findById(String id) {
        return super.findById(id);
    }

    @Override
    public void complete(ProjectDTO project) {
        project.setProjectStatus(Status.COMPLETE);

    }

    @Override
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {

        //one goal - build that ProjectDTO with allArgConstructor
        List<ProjectDTO>projectList =  findAll()
                .stream()
                .filter(project -> project.getAssignedManager().equals(manager))
                .map(
                        project ->{

                            List<TaskDTO>taskList = taskService.findTaskByManager(manager);
                            int completeTaskCount = (int) taskList.stream()
                                    .map(
                                            task -> task.getProject().equals(project) && task.getTaskStatus()==Status.COMPLETE
                                    ).count();

                            int unfinishedTaskCount = (int) taskList.stream()
                                    .map(
                                            task -> task.getProject().equals(project) && task.getTaskStatus()!=Status.COMPLETE
                                    ).count();



                            project.setCompleteTaskCount(completeTaskCount);
                            project.setUnfinishedTaskCount(unfinishedTaskCount);
                            return project;
                        }
                ).collect(Collectors.toList());

      return projectList;
    }
}
