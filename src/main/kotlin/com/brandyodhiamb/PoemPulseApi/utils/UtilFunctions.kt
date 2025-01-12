package com.brandyodhiamb.PoemPulseApi.utils

import com.brandyodhiamb.PoemPulseApi.feature.author.models.dto.AuthorPoemDto
import com.brandyodhiamb.PoemPulseApi.feature.author.models.entity.AuthorPoemEntity

//Util Functions
fun convertAuthorPoemEntityToAuthorPoemDto(authorPoemEntity: AuthorPoemEntity): AuthorPoemDto {
    return AuthorPoemDto(
        title = authorPoemEntity.title,
        author = authorPoemEntity.author,
        lines = authorPoemEntity.lines.split("\n"),
        lineCount = authorPoemEntity.lineCount
    )
}