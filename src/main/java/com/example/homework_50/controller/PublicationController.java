package com.example.homework_50.controller;

import com.example.homework_50.dto.PublicationDto;
import com.example.homework_50.service.PublicationService;
import org.springframework.web.bind.annotation.*;

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
    public PublicationDto getPublicationByID(@PathVariable String email){
        return publicationService.findPostByUser(email);
    }

    @GetMapping("subscriptions/{autorSubscriptions}")
    public List<PublicationDto> getPublicationBySubscriptions(@PathVariable String autorsSubscriptions){
        return publicationService.findPostBySubscriptions(autorsSubscriptions);
    }

    @PostMapping("/{description}")
    public void addPublication(@PathVariable String description, @RequestParam String email, String image){
        publicationService.addPublication(image,description,email);
    }

    @DeleteMapping("delete/{publicationID}")
    public void deletePublication(@PathVariable Long publicationID,String email){
        publicationService.deletePublication(publicationID,email);
    }
}
