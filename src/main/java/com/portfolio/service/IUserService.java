package com.portfolio.service;

import com.portfolio.dto.UserRequest;
import com.portfolio.dto.UserResponse;

public interface IUserService {

    UserResponse login(UserRequest userRequest);

}
