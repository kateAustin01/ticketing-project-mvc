package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectController {
    ProjectService projectService;
    UserService userService;


    public ProjectController(ProjectService projectService,UserService userService) {
        this.projectService = projectService;

    }


    @GetMapping("/create")
    public String createProject(Model model,UserDTO userDTO) {
    model.addAttribute("project", new ProjectDTO());
    model.addAttribute("projects", projectService.findAll());
    model.addAttribute("users", userService.findAll());
        return "/project/create";

    }
}
