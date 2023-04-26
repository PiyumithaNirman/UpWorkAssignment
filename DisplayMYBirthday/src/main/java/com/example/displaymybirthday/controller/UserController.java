package com.example.displaymybirthday.controller;

import com.example.displaymybirthday.dto.UserDTO;
import com.example.displaymybirthday.exception.AlreadyExistException;
import com.example.displaymybirthday.service.UserService;
import com.example.displaymybirthday.util.StandardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/registerUser")
    public ResponseEntity<StandardResponse> saveLearner(@RequestBody @Valid UserDTO userDTO) throws AlreadyExistException {

        String saveLearner = userService.saveUser(userDTO);
        LOGGER.info(saveLearner+" LOGGER");
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",saveLearner),
                HttpStatus.OK
        );
    }

    @GetMapping("getBirthday/{username}")
    public ResponseEntity<StandardResponse> getBirthday(@PathVariable("username") String userName){

        String getBirthday = userService.getBirthday(userName);
        LOGGER.info(getBirthday+" LOGGER");
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",getBirthday),
                HttpStatus.OK
        );
    }

    @PutMapping("changeBirthday")
    public ResponseEntity<StandardResponse> changeBirthday(@RequestBody @Valid UserDTO userDTO){

        String changeBirthday = userService.changeBirthday(userDTO);
        LOGGER.info(changeBirthday+" LOGGER");
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",changeBirthday),
                HttpStatus.OK
        );
    }
}
