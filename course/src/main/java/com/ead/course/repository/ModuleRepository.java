package com.ead.course.repository;

import com.ead.course.model.ModuleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<ModuleModel, UUID> {

  @Query(value = "select * from tb_modules where course_course_id = :courseId", nativeQuery = true)
  List<ModuleModel> findAllModulesIntoCourse(@Param("courseId") UUID courseId);

  @Query(value = "select * from tb_modules where module_id = :moduleId and course_course_id = :courseId", nativeQuery = true)
  Optional<ModuleModel> findModuleIntoCourse(@Param("courseId") UUID courseId, @Param("moduleId") UUID moduleId);
}
