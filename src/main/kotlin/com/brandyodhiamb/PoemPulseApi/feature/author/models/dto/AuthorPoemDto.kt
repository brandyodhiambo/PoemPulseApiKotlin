package com.brandyodhiamb.PoemPulseApi.feature.author.models.dto

data class AuthorPoemDto(
    val title: String,
    val author: String,
    val lines: List<String>,
    val lineCount: Int
)
