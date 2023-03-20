package com.example.homework_50.entity;

import com.example.homework_50.util.Generator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private String author;
    private String text;
    private Date timeOfComment;
    public static Comment random(User user) {
        return builder()
                .author(user.getEmail())
                .text(Generator.makeDescription())
                .timeOfComment(new Date(Math.abs(System.currentTimeMillis() - RandomUtils.nextLong())))
                .build();
    }
}
