package com.ead.course.clients;

import com.ead.course.dto.CourseUserDto;
import com.ead.course.dto.ResponsePageDto;
import com.ead.course.dto.UserDto;
import com.ead.course.service.UtilsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Log4j2
@Component
public class AuthUserClient {
  @Autowired
  RestTemplate restTemplate;

  @Autowired
  private UtilsService utilsService;

  public Page<UserDto> getAllUsersByCourse(UUID courseId, Pageable pageable){
    List<UserDto> searchResult = null;
    String url = utilsService.createUrl(courseId, pageable);
    log.debug("Request URL: {}", url);
    try{
      ParameterizedTypeReference<ResponsePageDto<UserDto>> responseType = new ParameterizedTypeReference<>() {};
      ResponseEntity<ResponsePageDto<UserDto>> result = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
      searchResult = Objects.requireNonNull(result.getBody()).getContent();
    }catch(HttpStatusCodeException e){
      log.error("Error Request/users {}", e);
    }

    return new PageImpl<>(searchResult);
  }

  public ResponseEntity<UserDto> getOneUserById(UUID userId){
    String url =  utilsService.getRequestUrl().concat("/users/" + userId);
    return restTemplate.exchange(url, HttpMethod.GET, null, UserDto.class);
  }

  public void postSubscriptionUserInCourse(UUID courseId, UUID userId) {
    String requestUrl = utilsService.getRequestUrl().concat("/users/").concat(userId.toString()).concat("/courses/subscription");
    var courseUserDto = new CourseUserDto(courseId, userId);
    restTemplate.postForObject(requestUrl, courseUserDto, String.class);
  }
}
