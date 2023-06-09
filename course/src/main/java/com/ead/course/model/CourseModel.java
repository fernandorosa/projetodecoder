package com.ead.course.model;

import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_COURSES")
public class CourseModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID courseId;
  @Column(nullable = false, length = 150)
  private String name;
  @Column(nullable = false, length = 250)
  private String description;
  private String imageUrl;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
  @Column(nullable = false)
  private LocalDateTime creationDate;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
  @Column(nullable = false)
  private LocalDateTime lastUpdateDate;
  @Enumerated(EnumType.STRING)
  private CourseStatus courseStatus;
  @Enumerated(EnumType.STRING)
  private CourseLevel courseLevel;
  @Column(nullable = false)
  private UUID userInstructor;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
  @Fetch(FetchMode.SUBSELECT)
  private Set<ModuleModel> modules;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
  private Set<CourseUserModel> coursesUsers;

  public CourseUserModel convertToCourseUserModel(UUID userId){
    return new CourseUserModel(null, this, userId);
  }
}
