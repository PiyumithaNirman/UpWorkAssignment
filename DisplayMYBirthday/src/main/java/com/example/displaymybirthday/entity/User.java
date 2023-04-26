package com.example.displaymybirthday.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Table(name = "user")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "user_Id",length = 45,unique = true)
        private int userId;

        @Column(name = "user_name",length = 100,nullable = false,unique = true)
        @NotBlank(message = "User name should not be null")
        @NotNull
        private String userName;

        @Column(name="user_birthday")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        @NotBlank(message = "Birthday should not be null")
        @NotNull
        private LocalDate birthday;

    public User(String userName, LocalDate birthday) {
        this.userName = userName;
        this.birthday = birthday;
    }
}
