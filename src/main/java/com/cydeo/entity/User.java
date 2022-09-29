package com.cydeo.entity;

import com.cydeo.enums.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
@Data doesn't look at other classes.
They don't know about your superclass's non-zero-arg constructor. That is why if you are extending another
class you need to create a constructor yourself,@Data doesn't work in this situation
*/
@NoArgsConstructor
@Data
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private boolean enabled;
    private String phone;
    private Role role;
    private Gender gender;

    public User(Long ID, LocalDateTime insertDateTime, Long insertUserID, LocalDateTime lastUpdateDateTime, Long lastUpdateUserID, String firstName, String lastName, String userName, String password, boolean enabled, String phone, Role role, Gender gender) {
        super(ID, insertDateTime, insertUserID, lastUpdateDateTime, lastUpdateUserID);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
        this.phone = phone;
        this.role = role;
        this.gender = gender;
    }


}
