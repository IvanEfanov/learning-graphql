package ru.learning.graphql.controller

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller
import ru.learning.graphql.dto.AuthorCreationRequest
import ru.learning.graphql.model.Author
import ru.learning.graphql.repository.AuthorRepository

@Controller
class MutationController(
    private val authorRepository: AuthorRepository,
) {

    @MutationMapping("createAuthor")
    fun createAuthor(@Argument("author") dto: AuthorCreationRequest): Author =
        authorRepository.createAuthor(dto)
}
