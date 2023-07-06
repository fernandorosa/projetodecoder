package com.ead.course.service.impl;

import com.ead.course.model.LessonModel;
import com.ead.course.repository.LessonRepository;
import com.ead.course.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LessonServiceImpl implements LessonService {

  @Autowired
  LessonRepository lessonRepository;

  @Override
  public LessonModel save(LessonModel lessonModel) {
    return lessonRepository.save(lessonModel);
  }

  @Override
  public Optional<LessonModel> findLessonIntoModule(UUID moduleId, UUID lessonId) {
    return lessonRepository.findLessonIntoModule(moduleId, lessonId);
  }

  @Override
  public void delete(LessonModel lessonModel) {
    lessonRepository.delete(lessonModel);
  }

  @Override
  public List<LessonModel> findAllByModule(UUID moduleId) {
    return lessonRepository.findAllLessonsIntoModule(moduleId);
  }

  @Override
  public Page<LessonModel> findAllByCourse(Specification<LessonModel> spec, Pageable pageable) {
    return lessonRepository.findAll(spec, pageable);
  }
}
