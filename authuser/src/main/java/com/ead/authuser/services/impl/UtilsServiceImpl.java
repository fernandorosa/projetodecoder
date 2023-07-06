package com.ead.authuser.services.impl;

import com.ead.authuser.services.UtilsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtilsServiceImpl implements UtilsService {
  @Value("${ead.api.url.course}")
  private String REQUEST_URI;

  @Override
  public String createUrl(UUID userId, Pageable pageable){
    return REQUEST_URI
      .concat("/courses?userId="+userId)
      .concat("&page="+pageable.getPageNumber())
      .concat("&size="+pageable.getPageSize())
      .concat("&sort="+pageable.getSort().toString().replaceAll(":", ","));
  }

  @Override
  public String getRequestUrl(){
    return REQUEST_URI;
  }
}
