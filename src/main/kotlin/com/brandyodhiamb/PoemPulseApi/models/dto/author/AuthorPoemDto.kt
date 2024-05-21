package com.brandyodhiamb.PoemPulseApi.models.dto.author

data class AuthorPoemDto(
    val title: String,
    val author: String,
    val lines: List<String>,
    val lineCount: Int
)
