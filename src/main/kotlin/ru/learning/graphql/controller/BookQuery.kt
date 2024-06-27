package ru.learning.graphql.controller

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.BatchMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import ru.learning.graphql.exception.BookNotFoundException
import ru.learning.graphql.model.Author
import ru.learning.graphql.model.Book
import ru.learning.graphql.repository.AuthorRepository
import ru.learning.graphql.repository.BookRepository

@Controller
class BookQuery(
    private val bookRepository: BookRepository,
    private val authorRepository: AuthorRepository,
) {

    @QueryMapping("allBooks")
    fun allBooks(@Argument("limit") limit: Int): List<Book> =
        bookRepository.getAll(limit)

    @QueryMapping
    fun getBookById(@Argument id: Int): Book =
        bookRepository.findById(id)
            ?: throw BookNotFoundException(id)

    @BatchMapping(typeName = "Book", field = "author")
    fun author(books: List<Book>): Map<Book, Author> {
        val ids = books.map { it.authorId }.toSet()
        val authors = authorRepository.getAllByIds(ids)
        return books.associateWith { authors.getValue(it.authorId) }
    }
}