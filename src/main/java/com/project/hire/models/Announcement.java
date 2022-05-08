package com.project.hire.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "announcements")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "VARCHAR(10000)")
    private String description;
    private String address;
    private double hourPrice;

    @OneToMany
    private List<File> photos = new ArrayList<>();

    @OneToMany
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    private User publisher;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Announcement that = (Announcement) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
