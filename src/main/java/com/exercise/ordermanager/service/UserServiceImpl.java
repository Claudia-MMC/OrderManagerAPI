package com.exercise.ordermanager.service;

import com.exercise.ordermanager.dto.UserDTO;
import com.exercise.ordermanager.entity.User;
import com.exercise.ordermanager.mapper.UserMapper;
import com.exercise.ordermanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Override
    public UserDTO findById(Long id) {
        return UserMapper.userToUserDto(returnUser(id));
    }

    @Override
    public List<UserDTO> findAll() {
        return UserMapper.userListToUserDtoList(userRepository.findAll());
    }

    @Override
    public UserDTO register(UserDTO userDTO) {
        try {
            User user = UserMapper.userDtoToUser(userDTO);
            logger.info("User created. ID: " + user.getId());
            return UserMapper.userToUserDto(userRepository.save(user));
        } catch (Exception e) {
            logger.error("Error creating user: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) {
        try {
            User user = returnUser(id);
            if (Objects.nonNull(user)) {
                UserMapper.update(user, userDTO);
                logger.info("User updated. ID: " + user.getId());
                return UserMapper.userToUserDto(userRepository.save(user));
            } else {
                logger.error("User not found. ID: " + id);
                throw new ObjectNotFoundException("User not found: ID=" + id, user);
            }
        } catch (Exception e) {
            logger.error("Error updating user: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public String delete(Long id) {
        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                logger.info("User deleted. ID: " + id);
                return "User id:" + id + "successfully deleted";
            } else {
                logger.error("User not found. ID: " + id);
                return "User id:" + id + "not found";
            }
        } catch (Exception e) {
            logger.error("Error deleting user: " + e.getMessage(), e);
            throw e;
        }
    }

    private User returnUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Stock Movement not found"));
    }
}
