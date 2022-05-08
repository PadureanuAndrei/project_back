package com.project.hire.services;

import com.project.hire.converters.AnnouncementConverter;
import com.project.hire.dto.ReviewDto;
import com.project.hire.dto.annoncement.AnnouncementDto;
import com.project.hire.dto.annoncement.CreateAnnouncementDto;
import com.project.hire.exceptions.NotFoundException;
import com.project.hire.models.Announcement;
import com.project.hire.models.File;
import com.project.hire.models.Review;
import com.project.hire.models.User;
import com.project.hire.repositories.AnnouncementRepository;
import com.project.hire.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;
    private final UserRepository userRepository;
    private final FileService fileService;
    private final AnnouncementConverter announcementConverter;

    @Transactional
    public AnnouncementDto getAnnouncement(long id) {
        Announcement announcement = findAnnouncement(id);
        return announcementConverter.convert(announcement);
    }

    @Transactional
    public void addReview(long announcementId, ReviewDto dto) {
        Announcement announcement = findAnnouncement(announcementId);
        User reviewer = userRepository.findById(dto.getReviewer())
                .orElseThrow(NotFoundException::new);

        Review review = new Review();
        review.setMessage(dto.getMessage());
        review.setReviewer(reviewer);

        announcement.getReviews().add(review);

        announcementRepository.save(announcement);
    }

    @Transactional
    public AnnouncementDto addAnnouncement(CreateAnnouncementDto dto, String publisherEmail) {
        User publisher = findUser(publisherEmail);

        Announcement announcement = new Announcement();
        announcement.setTitle(dto.getTitle());
        announcement.setDescription(dto.getDescription());
        announcement.setAddress(dto.getAddress());
        announcement.setHourPrice(dto.getHourPrice());

        List<File> photos = dto.getPhotos().stream()
                .map(fileService::saveFile)
                .collect(Collectors.toList());
        announcement.setPhotos(photos);
        announcement.setPublisher(publisher);

        announcement = announcementRepository.save(announcement);

        return announcementConverter.convert(announcement);
    }

    @Transactional
    public AnnouncementDto updateAnnouncement(long announcementId, CreateAnnouncementDto dto) {
        Announcement announcement = findAnnouncement(announcementId);

        if (dto.getAddress() != null) {
            announcement.setAddress(dto.getAddress());
        }
        if (dto.getHourPrice() != null) {
            announcement.setHourPrice(dto.getHourPrice());
        }
        if (dto.getPhotos() != null) {
            List<File> photos = dto.getPhotos().stream()
                    .map(fileService::saveFile)
                    .collect(Collectors.toList());
            announcement.setPhotos(photos);
        }

        announcement = announcementRepository.save(announcement);

        return announcementConverter.convert(announcement);
    }

    @Transactional
    public List<AnnouncementDto> getAnnouncements() {
        return announcementRepository.findAll().stream()
                .map(announcementConverter::convert)
                .collect(Collectors.toList());
    }
    @Transactional List<AnnouncementDto> getAnnouncementsByPublisherId(long id) {
        return announcementRepository.findAllByPublisher_Id(id).stream()
                .map(announcementConverter::convert)
                .collect(Collectors.toList());
    }

    Announcement findAnnouncement(long id) {
        return announcementRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    User findUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(NotFoundException::new);
    }
}
