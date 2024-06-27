package ru.learning.graphql.repository

import org.springframework.stereotype.Repository
import ru.learning.graphql.dto.AuthorCreationRequest
import ru.learning.graphql.model.Author
import java.time.LocalDate

@Repository
class AuthorRepository {

    fun getAllByIds(ids: Set<Int>): Map<Int, Author> =
        AUTHORS
            .filterKeys { it in ids }
            .also { println("Get by ids: $ids") }

    fun createAuthor(dto: AuthorCreationRequest): Author {
        val author = Author(
            id = AUTHORS.maxOf { it.key } + 1,
            firstName = dto.firstName,
            lastName = dto.lastName,
            birthDate = dto.birthDate,
        )
        AUTHORS[author.id] = author
        return author
    }

    private companion object {
        val AUTHORS = listOf(
            Author(1, "Лев", "Толстой", LocalDate.of(1828, 9, 9)),
            Author(2, "Александр", "Пушкин", LocalDate.of(1799, 6, 6)),
        )
            .associateBy { it.id }
            .toMutableMap()
    }
}
