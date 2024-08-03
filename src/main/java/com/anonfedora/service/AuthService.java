package com.anonfedora.service;

import com.anonfedora.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
