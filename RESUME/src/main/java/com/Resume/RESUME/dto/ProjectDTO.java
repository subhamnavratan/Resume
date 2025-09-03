package com.Resume.RESUME.dto;

public class ProjectDTO {
    private String title;
    private String description;
    private String link;

    public ProjectDTO() {}

    public ProjectDTO(String title, String description, String link) {
        this.title = title;
        this.description = description;
        this.link = link;
    }

    // Getters & Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
}
