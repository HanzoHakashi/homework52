package com.example.homework_50.util;


import com.example.homework_50.dao.*;
import com.example.homework_50.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Configuration
public class InitDatabase {

    private static final Random r = new Random();

    @Bean
    CommandLineRunner init(UserDao userDao, PublicationDao publicationDao, CommentDao commentDao, LikesDao likesDao, SubscriptionDao subscriptionDao) {
        return (args) -> {
            userDao.createTable();
            publicationDao.createTable();
            commentDao.createTable();
            likesDao.createTable();
            subscriptionDao.createTable();

            userDao.deleteAll();
            publicationDao.deleteAll();
            commentDao.deleteAll();
            likesDao.deleteAll();
            subscriptionDao.deleteAll();

            List<User> users = Stream.generate(User::random)
                    .limit(10)
                    .collect(toList());
            userDao.saveAll(users);
            List<Publication> publications = new ArrayList<>();
            users.forEach(user -> {
                selectRandomUser(users,r.nextInt(3)+1).stream()
                        .map(user1 -> Publication.random(pickRandom(users)))
                        .peek(publications::add)
                        .forEach(publicationDao::save);
                        });


//            List<Likes> likes = new ArrayList<>();
//            users.forEach(user -> {
//                selectRandomPublication(publications,r.nextInt(3)+1).stream()
//                        .map(publication -> Likes.random(publication,user))
//                        .peek(likes::add)
//                        .forEach(likesDao::save);
//            });
//            List<Comment> comments = new ArrayList<>();
//            users.forEach(user -> {
//                selectRandomUser(users,r.nextInt(3)+1).stream()
//                        .map(user1 -> Comment.random(user))
//                        .peek(comments::add)
//                        .forEach(commentDao::save);
//            });
//
//            List<Subscriptions> subscriptions = new ArrayList<>();
//            users.forEach(user -> {
//                selectRandomUser(users,r.nextInt(3)+1).stream()
//                        .map(user1 -> Subscriptions.random(user,user1,user1))
//                        .peek(subscriptions::add)
//                        .forEach(subscriptionDao::save);
//            });

//            List<Review> reviews = new ArrayList<>();
//
//            users.forEach(user -> {
//                selectRandomMovies(movies, r.nextInt(3)+1).stream()
//                        .map(movie -> Review.random(user, movie))
//                        .peek(reviews::add)
//                        .forEach(reviewDao::save);
//            });

        };
    }

    private List<User> selectRandomUser(List<User> users, int amountOfMovies) {
        return Stream.generate(() -> pickRandom(users))
                .distinct()
                .limit(amountOfMovies)
                .collect(toList());
    }

    private List<Publication> selectRandomPublication(List<Publication> publications, int amountOfMovies) {
        return Stream.generate(() -> pickRandomPublication(publications))
                .distinct()
                .limit(amountOfMovies)
                .collect(toList());
    }

    private static User pickRandom(List<User> users) {
        return users.get(r.nextInt(users.size()));
    }
    private static Publication pickRandomPublication(List<Publication> publications) {
        return publications.get(r.nextInt(publications.size()));
    }



}
