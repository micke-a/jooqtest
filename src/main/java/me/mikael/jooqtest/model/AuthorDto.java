package me.mikael.jooqtest.model;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder(toBuilder = true)
public record AuthorDto(Long id, String firstName, String lastName, LocalDate dateOfBirth, int yearOfBirth,
                        String address, List<BookDto> books) {
}
