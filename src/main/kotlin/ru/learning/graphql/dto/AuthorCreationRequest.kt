package ru.learning.graphql.dto

import java.time.LocalDate

data class AuthorCreationRequest(
    val firstName: String,
    val lastName: String,
    val birthDate: LocalDate,
)
