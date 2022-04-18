package com.surri.springboot.users;

import java.util.Optional;

import com.surri.springboot.users.Entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    public Page<User> findAll() {
        Pageable pageable = PageRequest.of(0,3);
        return usersRepository.findAll(pageable);
    }

    public Optional<User> findById(Long id) {
        return usersRepository.findById(id);
    }
}
