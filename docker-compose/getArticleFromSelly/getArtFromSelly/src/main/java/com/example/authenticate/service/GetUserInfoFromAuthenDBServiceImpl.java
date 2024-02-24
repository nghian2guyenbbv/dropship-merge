package com.example.authenticate.service;

import com.example.authenticate.dto.UserSellyDto;
import com.example.authenticate.repositories.UserSellyRepo;
import com.example.authenticate.sellyUser.SellyUser;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserInfoFromAuthenDBServiceImpl implements GetUserInfoFromAuthenDBService {
    @Autowired
    private UserSellyRepo userSellyRepo;

    @Override
    public SellyUser getSellyUserFromDb() {
        List<UserSellyDto> users = userSellyRepo.findAll();
        return CollectionUtils.emptyIfNull(users).stream().findFirst().map(userDto ->
                SellyUser.builder().userName(userDto.getUserName()).passWord(userDto.getPassword()).build()
        ).orElse(null);
    }
}
