package com.example.displaymybirthday.service;

import com.example.displaymybirthday.dto.UserDTO;
import com.example.displaymybirthday.exception.AlreadyExistException;

public interface UserService {
    String saveUser(UserDTO userDTO) throws AlreadyExistException;

    String getBirthday(String userName);

    String changeBirthday(UserDTO userDTO);
}
