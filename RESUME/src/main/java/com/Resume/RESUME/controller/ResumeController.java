package com.Resume.RESUME.controller;

import com.Resume.RESUME.dto.*;
import com.Resume.RESUME.model.ContactDetail;
import com.Resume.RESUME.model.EducationDetail;
import com.Resume.RESUME.model.ProjectDetail;
import com.Resume.RESUME.model.Resume;
import com.Resume.RESUME.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    // ---------- CREATE RESUME ----------
    @PostMapping
    public ResponseEntity<Resume> createResume(@RequestBody Resume resume) {
        return ResponseEntity.ok(resumeService.saveResume(resume));
    }

    // ---------- GET ALL RESUMES ----------
    @GetMapping
    public ResponseEntity<List<Resume>> getAllResumes() {
        return ResponseEntity.ok(resumeService.getAllResumes());
    }

    // ---------- GET RESUMES BY NAME ----------
    @GetMapping("/{name}")
    public ResponseEntity<List<Resume>> getResumesByName(@PathVariable String name) {
        List<Resume> resumes = resumeService.getResumesByName(name);
        if (resumes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resumes);
    }

    // ---------- DELETE RESUME BY NAME ----------
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteResume(@PathVariable String name) {
        resumeService.deleteResumeByName(name);
        return ResponseEntity.ok("Resume(s) deleted with Name: " + name);
    }

    // ---------- CONTACT DETAILS ----------
    @GetMapping("/{name}/contact")
    public ResponseEntity<ContactDetail> getContactDetail(@PathVariable String name) {
        return ResponseEntity.ok(resumeService.getContactDetailByName(name));
    }

    @PutMapping("/{name}/contact")
    public ResponseEntity<ContactDetail> updateContactDetail(@PathVariable String name, @RequestBody ContactDTO dto) {
        return ResponseEntity.ok(resumeService.updateContactByName(name, dto));
    }

    // ---------- EDUCATION DETAILS ----------
    @GetMapping("/{name}/education")
    public ResponseEntity<List<EducationDetail>> getEducationDetails(@PathVariable String name) {
        return ResponseEntity.ok(resumeService.getEducationDetailsByName(name));
    }

    @PutMapping("/{name}/education")
    public ResponseEntity<List<EducationDetail>> updateEducationDetails(@PathVariable String name,
                                                                        @RequestBody List<EducationDTO> dtos) {
        return ResponseEntity.ok(resumeService.updateEducationByName(name, dtos));
    }

    // ---------- SKILLS ----------
    @GetMapping("/{name}/skills")
    public ResponseEntity<List<String>> getSkills(@PathVariable String name) {
        return ResponseEntity.ok(resumeService.getSkillsByName(name));
    }

    @PutMapping("/{name}/skills")
    public ResponseEntity<List<String>> updateSkills(@PathVariable String name, @RequestBody SkillsDTO dto) {
        return ResponseEntity.ok(resumeService.updateSkillsByName(name, dto));
    }

    // ---------- PROJECTS ----------
    @GetMapping("/{name}/projects")
    public ResponseEntity<List<ProjectDetail>> getProjects(@PathVariable String name) {
        return ResponseEntity.ok(resumeService.getProjectsByName(name));
    }

    @PutMapping("/{name}/projects")
    public ResponseEntity<List<ProjectDetail>> updateProjects(@PathVariable String name, @RequestBody List<ProjectDTO> dtos) {
        return ResponseEntity.ok(resumeService.updateProjectsByName(name, dtos));
    }

    // ---------- ACHIEVEMENT ----------
    @GetMapping("/{name}/achievement")
    public ResponseEntity<String> getAchievement(@PathVariable String name) {
        return ResponseEntity.ok(resumeService.getAchievementByName(name));
    }

    @PutMapping("/{name}/achievement")
    public ResponseEntity<String> updateAchievement(@PathVariable String name, @RequestBody AchievementDTO dto) {
        return ResponseEntity.ok(resumeService.updateAchievementByName(name, dto));
    }

    // ---------- ABOUT ----------
    @GetMapping("/{name}/about")
    public ResponseEntity<String> getAbout(@PathVariable String name) {
        return ResponseEntity.ok(resumeService.getAboutByName(name));
    }

    @PutMapping("/{name}/about")
    public ResponseEntity<String> updateAbout(@PathVariable String name, @RequestBody AboutDTO dto) {
        return ResponseEntity.ok(resumeService.updateAboutByName(name, dto));
    }
}



