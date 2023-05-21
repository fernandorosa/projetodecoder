package com.ead.authuser.services;

import com.ead.authuser.models.UserModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    public List<UserModel> findAll();

    public Optional<UserModel> findById(UUID userId);

    public void delete(UserModel user);

    public void save(UserModel userModel);

    public boolean existByUserName(String userName);
    public boolean existByEmail(String email);
}
