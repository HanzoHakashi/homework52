package com.example.homework_50.entity;

import com.example.homework_50.util.Generator;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String email;
    private String name;
    private String username;
    private String password;
    private int counter;

    public static User random() {
        return builder()
                .email(Generator.makeEmail())
                .name(Generator.makeName())
                .username(Generator.makeName())
                .password(Generator.makePassword())
                .build();
    }
}
