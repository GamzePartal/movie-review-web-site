package com.webproject.web.service;

import com.webproject.web.dto.DtoUser;
import com.webproject.web.jwt.AuthRequest;
import com.webproject.web.jwt.AuthResponse;

public interface IAuthService {

    DtoUser register(AuthRequest request);
    AuthResponse authenticate(AuthRequest request);
}
