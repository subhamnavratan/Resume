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
import java.util.Optional;
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

    // ---------- GET RESUME BY ID ----------
    public Optional<Resume> getResumeById(String id) {
        return resumeRepository.findById(id);
    }

    // ---------- DELETE RESUME ----------
    public void deleteResume(String id) {
        resumeRepository.deleteById(id);
    }

    // ---------- CONTACT DETAILS ----------
    public ContactDetail getContactDetail(String id) {
        return resumeRepository.findById(id)
                .map(Resume::getContactDetail)
                .orElse(null);
    }

    public ContactDetail updateContact(String id, ContactDTO dto) {
        Resume resume = resumeRepository.findById(id)
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
    public List<EducationDetail> getEducationDetails(String id) {
        return resumeRepository.findById(id)
                .map(Resume::getEducationDetails)
                .orElse(null);
    }

    public List<EducationDetail> updateEducation(String id, List<EducationDTO> dtos) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        List<EducationDetail> details = dtos.stream().map(dto -> {
            EducationDetail edu = new EducationDetail();
            edu.setLevel(dto.getLevel());          // <-- use level instead of degree
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
    public List<String> getSkills(String id) {
        return resumeRepository.findById(id)
                .map(Resume::getSkills)
                .orElse(null);
    }

    public List<String> updateSkills(String id, SkillsDTO dto) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        resume.setSkills(dto.getSkills());
        resumeRepository.save(resume);

        return resume.getSkills();
    }

    // ---------- GET ALL PROJECTS ----------
    public List<ProjectDetail> getProjects(String id) {
        return resumeRepository.findById(id)
                .map(Resume::getProjects)  // returns List<ProjectDetail>
                .orElse(null);
    }

    // ---------- UPDATE PROJECTS ----------
    public List<ProjectDetail> updateProjects(String id, List<ProjectDTO> dtos) {
        Resume resume = resumeRepository.findById(id)
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
    public String getAchievement(String id) {
        return resumeRepository.findById(id)
                .map(Resume::getAchievement)
                .orElse(null);
    }

    public String updateAchievement(String id, AchievementDTO dto) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        resume.setAchievement(dto.getAchievement());
        resumeRepository.save(resume);

        return resume.getAchievement();
    }

    // ---------- ABOUT ----------
    public String getAbout(String id) {
        return resumeRepository.findById(id)
                .map(Resume::getAbout)
                .orElse(null);
    }

    public String updateAbout(String id, AboutDTO dto) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        resume.setAbout(dto.getAbout());
        resumeRepository.save(resume);

        return resume.getAbout();
    }
}
