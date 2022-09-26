package com.cydeo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseEntity {
    private  Long ID;
    private LocalDateTime insertDateTime;
    private Long insertUserID;
    private LocalDateTime lastUpdateDateTime;
    private Long lastUpdateUserID;
}
