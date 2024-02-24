package com.example.authenticate.service;

import com.example.authenticate.dto.UserSellyDto;
import com.example.authenticate.repositories.UserSellyRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpdateTokenServiceImpl implements UpdateTokenService {

  @Autowired
  private UserSellyRepo userSellyRepo;

  @Override
  public String getTokenWithUser(String user) {
    System.out.println("user: " + user);
    UserSellyDto userSellyDto = userSellyRepo.findTokenByUserName(user);
    System.out.println("token from repo: " + userSellyDto.getToken());
    log.info("token from repo: {} for user {}" + userSellyDto.getToken(), user);
    return userSellyDto.getToken();
  }

  @Override
  public void updateTokenWithUser(String userName, String token) {
    userSellyRepo.updateTokenByUserName(userName, token);
  }
}
