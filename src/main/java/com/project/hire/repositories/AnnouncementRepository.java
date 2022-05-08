package com.project.hire.repositories;

import com.project.hire.models.Announcement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends CrudRepository<Announcement, Long> {
    List<Announcement> findAll();

    List<Announcement> findAllByPublisher_Id(long id);
}
