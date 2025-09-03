package com.Resume.RESUME.dto;

import java.util.List;

public class ProjectsDTO {
    private List<ProjectDTO> projects;

    public ProjectsDTO() {}

    public ProjectsDTO(List<ProjectDTO> projects) {
        this.projects = projects;
    }

    // Getter & Setter
    public List<ProjectDTO> getProjects() { return projects; }
    public void setProjects(List<ProjectDTO> projects) { this.projects = projects; }
}
