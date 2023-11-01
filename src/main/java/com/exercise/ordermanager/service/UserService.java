package com.exercise.ordermanager.service;

import com.exercise.ordermanager.dto.UserDTO;

import java.util.List;

public interface UserService {

    public UserDTO findById(Long id);

    public List<UserDTO> findAll();

    public UserDTO register(UserDTO userDTO);

    public UserDTO update(Long id, UserDTO userDTO);

    public String delete(Long id);
}
