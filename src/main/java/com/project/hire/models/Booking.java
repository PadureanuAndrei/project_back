package com.project.hire.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "bookings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Status status = Status.NEW;

    @ManyToOne(fetch = FetchType.LAZY)
    private User renter;

    @ManyToOne(fetch = FetchType.LAZY)
    private Announcement announcement;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void cancel() {
        status = Status.CANCELED;
    }

    public void accept() {
        status = Status.ACCEPTED;
    }

    public void reject() {
        status = Status.REJECTED;
    }

    public enum Status { NEW, ACCEPTED, REJECTED, CANCELED };
}
