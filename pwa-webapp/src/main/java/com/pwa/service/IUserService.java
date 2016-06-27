package com.pwa.service;

import com.pwa.model.User;

public interface IUserService extends BaseService {

    User findUserByUsername(String username);

}
