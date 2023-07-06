package com.ead.course.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_COURSES_USERS")
@AllArgsConstructor
@NoArgsConstructor
public class CourseUserModel implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private CourseModel course;
  @Column(nullable = false)
  private UUID userId;
}
