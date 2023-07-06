package com.ead.course.service;

import com.ead.course.model.ModuleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleService {

  void delete(ModuleModel moduleModel);

  ModuleModel save(ModuleModel moduleModel);

  Optional<ModuleModel> findModuleIntoCourse(UUID courseId, UUID moduleId);

  List<ModuleModel> findAll();

  List<ModuleModel> findAllByCourse(UUID courseId);

  Optional<ModuleModel> findById(UUID moduleId);

  Page<ModuleModel> findAllByCourse(Specification<ModuleModel> and, Pageable pageable);
}
