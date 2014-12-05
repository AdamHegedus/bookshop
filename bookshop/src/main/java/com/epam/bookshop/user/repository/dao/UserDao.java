package com.epam.bookshop.user.repository.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.epam.bookshop.user.repository.domain.UserEntity;

public interface UserDao extends CrudRepository<UserEntity, Long> {
    List<UserEntity> findByUsernameIgnoreCaseLike(String username);
}
