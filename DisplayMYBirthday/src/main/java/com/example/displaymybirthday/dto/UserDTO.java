package com.example.displaymybirthday.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private int userId;
    private String userName;
    private String birthday;

    public UserDTO(String userName, String birthday) {
        this.userName = userName;
        this.birthday = birthday;
    }
}
