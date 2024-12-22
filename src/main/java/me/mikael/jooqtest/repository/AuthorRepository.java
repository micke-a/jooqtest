package me.mikael.jooqtest.repository;

import lombok.RequiredArgsConstructor;
import me.mikael.jooqtest.db.tables.Author;
import me.mikael.jooqtest.db.tables.Book;
import me.mikael.jooqtest.model.AuthorDto;
import me.mikael.jooqtest.model.BookDto;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.jooq.impl.DSL.multiset;
import static org.jooq.impl.DSL.select;

@Service
@RequiredArgsConstructor
public class AuthorRepository {

    private final DSLContext dslContext;

    public List<AuthorDto> getAuthors() {

        return dslContext.select(
                        Author.AUTHOR.ID,
                        Author.AUTHOR.FIRST_NAME,
                        Author.AUTHOR.LAST_NAME,
                        Author.AUTHOR.DATE_OF_BIRTH,
                        Author.AUTHOR.YEAR_OF_BIRTH,
                        Author.AUTHOR.ADDRESS,
                        multiset(
                                select(Book.BOOK.AUTHOR_ID, Book.BOOK.TITLE)
                                        .from(Book.BOOK)
                                        .where(Book.BOOK.AUTHOR_ID.eq(Author.AUTHOR.ID))
                        ).as("books")
                                .convertFrom(b -> b.into(BookDto.class))
                )
                .from(Author.AUTHOR)
                .fetchInto(AuthorDto.class);
    }

    public Optional<AuthorDto> getAuthor(Long id) {

        return dslContext.select(
                        Author.AUTHOR.ID,
                        Author.AUTHOR.FIRST_NAME,
                        Author.AUTHOR.LAST_NAME,
                        Author.AUTHOR.DATE_OF_BIRTH,
                        Author.AUTHOR.YEAR_OF_BIRTH,
                        Author.AUTHOR.ADDRESS,
                        multiset(
                                select(Book.BOOK.AUTHOR_ID, Book.BOOK.TITLE)
                                        .from(Book.BOOK)
                                        .where(Book.BOOK.AUTHOR_ID.eq(Author.AUTHOR.ID))
                        ).as("books")
                                .convertFrom(b -> b.into(BookDto.class))
                )
                .from(Author.AUTHOR)
                .where(Author.AUTHOR.ID.eq(id))
                .fetchOptional()
                .map(r -> r.into(AuthorDto.class));
    }

    @Transactional
    public AuthorDto createAuthor(AuthorDto authorDto) {

        var author = dslContext.newRecord(Author.AUTHOR, authorDto);
        author.store();

        var persistedBooks = new ArrayList<BookDto>();
        if(authorDto.books()!=null) {
            for (BookDto b : authorDto.books()) {
                var book = dslContext.newRecord(Book.BOOK, b);
                book.setAuthorId(author.getId());
                book.store();

                persistedBooks.add(book.into(BookDto.class));
            }
        }

        return authorDto.toBuilder()
                .id(author.getId())
                .books(persistedBooks)
                .build();
    }

    public boolean existsById(Long id) {
        return dslContext.fetchExists(
                select(Author.AUTHOR.ID)
                        .from(Author.AUTHOR)
                        .where(Author.AUTHOR.ID.eq(id)));
    }

    @Transactional
    public void deleteById(Long id) {

        dslContext.deleteFrom(Book.BOOK)
                .where(Book.BOOK.AUTHOR_ID.eq(id))
                .execute();

        dslContext.deleteFrom(Author.AUTHOR)
                .where(Author.AUTHOR.ID.eq(id))
                .execute();
    }
}
