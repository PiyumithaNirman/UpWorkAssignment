package com.example.displaymybirthday.service.IMPL;

import com.example.displaymybirthday.dto.UserDTO;
import com.example.displaymybirthday.entity.User;
import com.example.displaymybirthday.exception.AlreadyExistException;
import com.example.displaymybirthday.exception.NotFoundException;
import com.example.displaymybirthday.repo.UserRepo;
import com.example.displaymybirthday.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public String saveUser(UserDTO userDTO) throws AlreadyExistException {

        String dateString = userDTO.getBirthday();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate userBirthday = LocalDate.parse(dateString, formatter);

        User user = new User(
                userDTO.getUserName(),
                userBirthday
        );

        if(userRepo.existsByUserName(userDTO.getUserName())){

            throw new AlreadyExistException(userDTO.getUserName() + " is already exist");
        }else
            userRepo.save(user);
            return "New user " + userDTO.getUserName() + " has been saved";
    }

    @Override
    public String getBirthday(String userName) {

        if(userRepo.existsByUserName(userName)){

            User user = userRepo.findByUserName(userName);

            LocalDate birthday = user.getBirthday();
            LocalDate now = LocalDate.now();

            LocalDate nextBirthday = birthday.withYear(now.getYear());

            if (nextBirthday.isEqual(now)) {

                return "Hello " +userName+ " Happy BirthDay";
            }else{
                long days = ChronoUnit.DAYS.between(now, nextBirthday);

                return "Hello " +userName+ " Your Birthday is in " +days+ " Days";
            }

        }
        return null;
    }

    @Override
    public String changeBirthday(UserDTO userDTO) {

        if(userRepo.existsByUserName(userDTO.getUserName())){

            User user = userRepo.findByUserName(userDTO.getUserName());

            String dateString = userDTO.getBirthday();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate userBirthday = LocalDate.parse(dateString, formatter);

            user.setBirthday(userBirthday);

            userRepo.save(user);

            return userDTO.getUserName()+"'s Birthday is Updated";
        }else

            throw new NotFoundException(userDTO.getUserName() + " is not found");

    }
}
