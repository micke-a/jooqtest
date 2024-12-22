package me.mikael.jooqtest.controller;

import lombok.RequiredArgsConstructor;
import me.mikael.jooqtest.model.AuthorDto;
import me.mikael.jooqtest.repository.AuthorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {

    private final AuthorRepository authorRepository;

    @GetMapping("/list")
    @ResponseBody
    public List<AuthorDto> list() {
        return authorRepository.getAuthors();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<AuthorDto> getAuthor(@PathVariable("id") Long id) {
        return authorRepository.getAuthor(id);
    }

    @PostMapping("/")
    @ResponseBody
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        return authorRepository.createAuthor(authorDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
