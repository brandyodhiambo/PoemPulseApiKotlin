package com.brandyodhiamb.PoemPulseApi.feature.dailypoem.service

import com.brandyodhiamb.PoemPulseApi.feature.author.models.dto.AuthorPoemDto
import com.brandyodhiamb.PoemPulseApi.feature.dailypoem.repository.DailyPoemRepository
import com.brandyodhiamb.PoemPulseApi.utils.convertAuthorPoemEntityToAuthorPoemDto
import org.springframework.stereotype.Service

@Service
class DailyPoemService(
    private val dailyPoemRepository: DailyPoemRepository
) {
    fun getRandomPoems(count: Int): List<AuthorPoemDto> {
        val randomPoems = dailyPoemRepository.findRandomPoems(count)
        return randomPoems.map { convertAuthorPoemEntityToAuthorPoemDto(it) }
    }
}