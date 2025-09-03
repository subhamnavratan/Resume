package com.Resume.RESUME.dto;

import java.util.List;

public class SkillsDTO {
    private List<String> skills;

    public SkillsDTO() {}

    public SkillsDTO(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
