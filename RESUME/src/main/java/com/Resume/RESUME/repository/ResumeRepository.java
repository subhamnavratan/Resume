package com.Resume.RESUME.repository;

import com.Resume.RESUME.model.Resume;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends MongoRepository<Resume, String> {
    List<Resume> findByName(String name);
    void deleteByName(String name);
}
