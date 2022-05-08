package com.project.hire.converters;

import com.project.hire.dto.ReviewDto;
import com.project.hire.dto.user.UserDto;
import com.project.hire.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserConverter implements Converter<User, UserDto> {
    private final ReviewConverter reviewConverter;

    @Override
    public UserDto convert(User user) {
        List<ReviewDto> reviews = user.getReviews().stream()
                .map(reviewConverter::convert)
                .collect(Collectors.toList());

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .reviews(reviews)
                .photo(user.getPhoto() != null ? user.getPhoto().getId() : null)
                .build();
    }
}
