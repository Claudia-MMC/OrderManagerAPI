package com.exercise.ordermanager.mapper;

import com.exercise.ordermanager.dto.UserDTO;
import com.exercise.ordermanager.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static User userDtoToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return user;
    }

    public static UserDTO userToUserDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    public static List<User> userDtoListToUserList(List<UserDTO> userDTOList) {
        return userDTOList.stream()
                .map(UserMapper::userDtoToUser)
                .collect(Collectors.toList());
    }

    public static List<UserDTO> userListToUserDtoList(List<User> userList) {
        return userList.stream()
                .map(UserMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    public static void update(User existingUser, UserDTO userDTO) {
        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
    }
}
