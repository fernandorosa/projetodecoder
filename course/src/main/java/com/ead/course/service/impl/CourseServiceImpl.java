package com.ead.course.service.impl;

import com.ead.course.model.CourseModel;
import com.ead.course.model.LessonModel;
import com.ead.course.model.ModuleModel;
import com.ead.course.repository.CourseRepository;
import com.ead.course.repository.LessonRepository;
import com.ead.course.repository.ModuleRepository;
import com.ead.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {

  @Autowired
  CourseRepository courseRepository;
  @Autowired
  ModuleRepository moduleRepository;

  @Autowired
  LessonRepository lessonRepository;

  @Transactional
  @Override
  public void delete(CourseModel courseModel) {
    List<ModuleModel> moduleModelList = moduleRepository.findAllModulesIntoCourse(courseModel.getCourseId());
    if(!CollectionUtils.isEmpty(moduleModelList)){
      moduleModelList.stream().forEach(module ->{
        List<LessonModel> lessonModelList = lessonRepository.findAllLessonsIntoModule(module.getModuleId());
        if(!CollectionUtils.isEmpty(lessonModelList)){
          lessonRepository.deleteAll(lessonModelList);
        }
      });
      moduleRepository.deleteAll(moduleModelList);
    }
    courseRepository.delete(courseModel);
  }

  @Override
  public CourseModel save(CourseModel courseModel) {
    return courseRepository.save(courseModel);
  }

  @Override
  public Optional<CourseModel> findById(UUID courseId) {
    return courseRepository.findById(courseId);
  }

  @Override
  public Page<CourseModel> findAll(Specification<CourseModel> spec, Pageable pageable) {
    return courseRepository.findAll(spec, pageable);
  }
}
