package com.exercise.ordermanager.controller;

import com.exercise.ordermanager.dto.UserDTO;
import com.exercise.ordermanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable(name = "id") Long id) {
        UserDTO createdUserDTO = userService.findById(id);
        if(Objects.nonNull(createdUserDTO)) {
            return ResponseEntity.ok().body(userService.findById(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<UserDTO> register(UserDTO userDTO, UriComponentsBuilder uriBuilder) {
        UserDTO createdUserDTO = userService.register(userDTO);
        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(createdUserDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(createdUserDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable(name = "id") Long id, @RequestBody UserDTO userDTO) {
        UserDTO createdUserDTO = userService.update(id, userDTO);
        if(Objects.nonNull(createdUserDTO)) {
            return ResponseEntity.ok().body(userService.update(id, userDTO));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(Long id) {
        return ResponseEntity.ok().body(userService.delete(id));
    }
}
