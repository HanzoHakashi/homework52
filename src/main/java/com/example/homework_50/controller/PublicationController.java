package com.example.homework_50.controller;

import com.example.homework_50.dto.PublicationDto;
import com.example.homework_50.service.PublicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/publication")
public class PublicationController {
    private final PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }
    @GetMapping
    public List<PublicationDto> findAll(){
        return publicationService.findAll();
    }
    @GetMapping("/{email}")
    public PublicationDto getPublicationByID(@RequestParam String email){
        return publicationService.findPostByUser(email);
    }

    @GetMapping("/{autorSubscriptions}")
    public List<PublicationDto> getPublicationBySubscriptions(@RequestParam String autorsSubscriptions){
        return publicationService.findPostBySubscriptions(autorsSubscriptions);
    }
}
