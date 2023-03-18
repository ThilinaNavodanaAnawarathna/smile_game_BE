package com.sliit.smile.service;

import com.sliit.smile.dto.User;

public interface AuthenticationService {
    User signInAndReturnJWT(User signInRequest);
}
