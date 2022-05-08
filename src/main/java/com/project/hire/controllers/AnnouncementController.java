package com.project.hire.controllers;

import com.project.hire.dto.annoncement.AnnouncementDto;
import com.project.hire.dto.annoncement.CreateAnnouncementDto;
import com.project.hire.services.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/announcements")
@RequiredArgsConstructor
@CrossOrigin
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @GetMapping("/{id}")
    public AnnouncementDto getAnnouncementById(@PathVariable long id) {
        return announcementService.getAnnouncement(id);
    }

    @PostMapping
    public AnnouncementDto addAnnouncement(@RequestBody CreateAnnouncementDto announcementDto, Principal principal) {
        String publisherEmail = principal.getName();

        return announcementService.addAnnouncement(announcementDto, publisherEmail);
    }

    @GetMapping()
    public List<AnnouncementDto> getAnnouncements() {
        return announcementService.getAnnouncements();
    }
}
