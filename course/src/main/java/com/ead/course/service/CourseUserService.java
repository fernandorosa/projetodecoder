package com.ead.course.service;

import com.ead.course.model.CourseModel;
import com.ead.course.model.CourseUserModel;

import java.util.UUID;

public interface CourseUserService {
  boolean existsByCourseAndUserId(CourseModel courseModel, UUID userId);

  CourseUserModel save(CourseUserModel courseUserModel);

  CourseUserModel saveAndSendSubscriptionUserInCourse(CourseUserModel courseUserModel);
}
