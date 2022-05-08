package com.project.hire.repositories;

import com.project.hire.models.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    @Query("select b from Booking b where b.renter.id = ?1 and b.startTime >= ?2")
    List<Booking> getBookingsByRenterId(long id, LocalDateTime start);

    @Query("select b from Booking b where b.renter.id = ?1 and (b.startTime between ?2 and ?3 or b.endTime between ?2 and ?3)")
    List<Booking> getBookingsByRenterId(long id, LocalDateTime start, LocalDateTime end);

    @Query("select b from Booking b where b.announcement.id = ?1 and b.startTime >= ?2")
    List<Booking> getBookingsByAnnouncementId(long id, LocalDateTime start);

    @Query("select b from Booking b where b.announcement.id = ?1 and (b.startTime between ?2 and ?3 or b.endTime between ?2 and ?3)")
    List<Booking> getBookingsByAnnouncementId(long id, LocalDateTime start, LocalDateTime end);

    Optional<Booking> getBookingById(long id);
}
