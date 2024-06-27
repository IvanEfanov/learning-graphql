package ru.learning.graphql.model

import java.time.LocalDate

data class Author(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val birthDate: LocalDate,
)
