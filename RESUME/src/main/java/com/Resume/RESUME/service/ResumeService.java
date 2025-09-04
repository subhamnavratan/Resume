package com.Resume.RESUME.service;

import com.Resume.RESUME.dto.*;
import com.Resume.RESUME.model.ContactDetail;
import com.Resume.RESUME.model.EducationDetail;
import com.Resume.RESUME.model.ProjectDetail;
import com.Resume.RESUME.model.Resume;
import com.Resume.RESUME.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    // ---------- CREATE OR UPDATE RESUME ----------
    public Resume saveResume(Resume resume) {
        return resumeRepository.save(resume);
    }

    // ---------- GET ALL RESUMES ----------
    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }

    // ---------- GET RESUMES BY NAME ----------
    public List<Resume> getResumesByName(String name) {
        return resumeRepository.findByName(name);
    }

    // ---------- DELETE RESUMES BY NAME ----------
    public void deleteResumeByName(String name) {
        resumeRepository.deleteByName(name);
    }

    // ---------- CONTACT DETAILS ----------
    public ContactDetail getContactDetailByName(String name) {
        return resumeRepository.findByName(name).stream()
                .findFirst()
                .map(Resume::getContactDetail)
                .orElse(null);
    }

    public ContactDetail updateContactByName(String name, ContactDTO dto) {
        Resume resume = resumeRepository.findByName(name).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        ContactDetail contact = resume.getContactDetail();
        if (contact == null) contact = new ContactDetail();

        contact.setEmail(dto.getEmail());
        contact.setPhoneNumber(dto.getPhoneNumber());
        contact.setLinkedin(dto.getLinkedin());
        contact.setGithub(dto.getGithub());
        contact.setAddress(dto.getAddress());

        resume.setContactDetail(contact);
        resumeRepository.save(resume);
        return contact;
    }

    // ---------- EDUCATION DETAILS ----------
    public List<EducationDetail> getEducationDetailsByName(String name) {
        return resumeRepository.findByName(name).stream()
                .findFirst()
                .map(Resume::getEducationDetails)
                .orElse(null);
    }

    public List<EducationDetail> updateEducationByName(String name, List<EducationDTO> dtos) {
        Resume resume = resumeRepository.findByName(name).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        List<EducationDetail> details = dtos.stream().map(dto -> {
            EducationDetail edu = new EducationDetail();
            edu.setLevel(dto.getLevel());
            edu.setInstitution(dto.getInstitution());
            edu.setYear(dto.getYear());
            edu.setGrade(dto.getGrade());
            return edu;
        }).collect(Collectors.toList());

        resume.setEducationDetails(details);
        resumeRepository.save(resume);
        return details;
    }

    // ---------- SKILLS ----------
    public List<String> getSkillsByName(String name) {
        return resumeRepository.findByName(name).stream()
                .findFirst()
                .map(Resume::getSkills)
                .orElse(null);
    }

    public List<String> updateSkillsByName(String name, SkillsDTO dto) {
        Resume resume = resumeRepository.findByName(name).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        resume.setSkills(dto.getSkills());
        resumeRepository.save(resume);

        return resume.getSkills();
    }

    // ---------- PROJECTS ----------
    public List<ProjectDetail> getProjectsByName(String name) {
        return resumeRepository.findByName(name).stream()
                .findFirst()
                .map(Resume::getProjects)
                .orElse(null);
    }

    public List<ProjectDetail> updateProjectsByName(String name, List<ProjectDTO> dtos) {
        Resume resume = resumeRepository.findByName(name).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        List<ProjectDetail> projects = dtos.stream().map(dto -> {
            ProjectDetail project = new ProjectDetail();
            project.setTitle(dto.getTitle());
            project.setDescription(dto.getDescription());
            project.setLink(dto.getLink());
            return project;
        }).collect(Collectors.toList());

        resume.setProjects(projects);
        resumeRepository.save(resume);

        return projects;
    }

    // ---------- ACHIEVEMENT ----------
    public String getAchievementByName(String name) {
        return resumeRepository.findByName(name).stream()
                .findFirst()
                .map(Resume::getAchievement)
                .orElse(null);
    }

    public String updateAchievementByName(String name, AchievementDTO dto) {
        Resume resume = resumeRepository.findByName(name).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        resume.setAchievement(dto.getAchievement());
        resumeRepository.save(resume);

        return resume.getAchievement();
    }

    // ---------- ABOUT ----------
    public String getAboutByName(String name) {
        return resumeRepository.findByName(name).stream()
                .findFirst()
                .map(Resume::getAbout)
                .orElse(null);
    }

    public String updateAboutByName(String name, AboutDTO dto) {
        Resume resume = resumeRepository.findByName(name).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        resume.setAbout(dto.getAbout());
        resumeRepository.save(resume);

        return resume.getAbout();
    }
}

