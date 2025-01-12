package com.brandyodhiamb.PoemPulseApi.feature.author.models.model


data class AuthorPoem(
    val title: String,
    val author: String,
    val lines: List<String>,
    val lineCount: Int
)
