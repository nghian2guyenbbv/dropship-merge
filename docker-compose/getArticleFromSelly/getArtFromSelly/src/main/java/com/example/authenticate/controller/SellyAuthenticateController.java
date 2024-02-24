package com.example.authenticate.controller;

import com.example.authenticate.login.service.SellyLoginService;
import com.example.authenticate.sellyUser.SellyUser;
import com.example.authenticate.service.GetUserInfoFromAuthenDBService;
import com.example.authenticate.service.UpdateTokenService;
import com.example.authenticate.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/sellyAuthenticate")
public class SellyAuthenticateController {
    private static final String NO_TOKEN = "NO_TOKEN";

    @Autowired
    private SellyLoginService sellyLoginService;

    @Autowired
    private UpdateTokenService updateTokenService;

    @Autowired
    private GetUserInfoFromAuthenDBService getUserInfoFromAuthenDBService;

    @PostMapping("/refreshToken")
    public Token getSellyToken(@RequestBody SellyUser sellyUser) {
        SellyUser userFromDb = getUserInfoFromAuthenDBService.getSellyUserFromDb();
        String tokenWithUser = updateTokenService.getTokenWithUser(userFromDb.getUserName());

        Optional<String> sellyToken = sellyLoginService.getTokenWithUserAndPass(userFromDb);
        sellyToken.ifPresent(sellyTk -> {
            if (!sellyTk.equals(tokenWithUser)) {
                updateTokenService.updateTokenWithUser(userFromDb.getUserName(), sellyTk);
            }
        });
        Token token = Token.builder().user(userFromDb.getUserName()).token(sellyToken.get()).build();
        //return token.getToken();
        return token;
    }

    @PostMapping("/getCurrentToken")
    public String getTokenFromDb(@RequestBody SellyUser sellyUser) {
        Optional<String> currentToken = sellyLoginService.getCurrentToken(sellyUser);
        Token token = Token.builder().user(sellyUser.getUserName()).token(currentToken.orElse(NO_TOKEN)).build();
        return token.getToken();
    }
}

