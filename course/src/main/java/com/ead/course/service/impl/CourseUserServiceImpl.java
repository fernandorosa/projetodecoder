package com.ead.course.service.impl;

import com.ead.course.clients.AuthUserClient;
import com.ead.course.model.CourseModel;
import com.ead.course.model.CourseUserModel;
import com.ead.course.repository.CourseUserRepository;
import com.ead.course.service.CourseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CourseUserServiceImpl implements CourseUserService {

  @Autowired
  private CourseUserRepository courseUserRepository;

  @Autowired
  private AuthUserClient authUserClient;

  @Override
  public boolean existsByCourseAndUserId(CourseModel courseModel, UUID userId) {
    return courseUserRepository.existsByCourseAndUserId(courseModel, userId);
  }

  @Override
  public CourseUserModel save(CourseUserModel courseUserModel) {
    return courseUserRepository.save(courseUserModel);
  }

  @Transactional
  @Override
  public CourseUserModel saveAndSendSubscriptionUserInCourse(CourseUserModel courseUserModel) {
    courseUserModel = courseUserRepository.save(courseUserModel);
    authUserClient.postSubscriptionUserInCourse(courseUserModel.getCourse().getCourseId(), courseUserModel.getUserId());
    return courseUserModel;
  }
}
