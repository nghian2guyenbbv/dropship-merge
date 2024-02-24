package com.example.authenticate.login.service;


import com.example.authenticate.sellyUser.SellyUser;

import java.util.Optional;

public interface SellyLoginService {

    Optional<String> getTokenWithUserAndPass(SellyUser sellyUser);

    Optional<String> getCurrentToken(SellyUser sellyUser);
}
