package com.Resume.RESUME.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "resumes")
public class Resume {

    @Id
    private String id;
    private String name;
    private String about;

    private ContactDetail contactDetail;
    private List<EducationDetail> educationDetails;
    private List<String> skills;
    private List<ProjectDetail> projects; // Multiple projects
    private String achievement;

    // Profiles
    private String leetcodeProfile;
    private String gfgProfile;

    public Resume() {}

    public Resume(String id, String name, String about, ContactDetail contactDetail,
                  List<EducationDetail> educationDetails, List<String> skills,
                  List<ProjectDetail> projects, String achievement,
                  String leetcodeProfile, String gfgProfile) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.contactDetail = contactDetail;
        this.educationDetails = educationDetails;
        this.skills = skills;
        this.projects = projects;
        this.achievement = achievement;
        this.leetcodeProfile = leetcodeProfile;
        this.gfgProfile = gfgProfile;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }

    public ContactDetail getContactDetail() { return contactDetail; }
    public void setContactDetail(ContactDetail contactDetail) { this.contactDetail = contactDetail; }

    public List<EducationDetail> getEducationDetails() { return educationDetails; }
    public void setEducationDetails(List<EducationDetail> educationDetails) { this.educationDetails = educationDetails; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }

    public List<ProjectDetail> getProjects() { return projects; }
    public void setProjects(List<ProjectDetail> projects) { this.projects = projects; }

    public String getAchievement() { return achievement; }
    public void setAchievement(String achievement) { this.achievement = achievement; }

    public String getLeetcodeProfile() { return leetcodeProfile; }
    public void setLeetcodeProfile(String leetcodeProfile) { this.leetcodeProfile = leetcodeProfile; }

    public String getGfgProfile() { return gfgProfile; }
    public void setGfgProfile(String gfgProfile) { this.gfgProfile = gfgProfile; }
}
