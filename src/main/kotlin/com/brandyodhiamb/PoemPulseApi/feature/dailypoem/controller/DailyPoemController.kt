package com.brandyodhiamb.PoemPulseApi.feature.dailypoem.controller

import com.brandyodhiamb.PoemPulseApi.feature.author.models.dto.AuthorPoemDto
import com.brandyodhiamb.PoemPulseApi.feature.dailypoem.service.DailyPoemService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/daily/poem")
class DailyPoemController(
    private val dailyPoemService: DailyPoemService
) {

    @GetMapping("/random/{count}")
    fun getRandomPoems(@PathVariable count: Int): ResponseEntity<List<AuthorPoemDto>> {
        val randomPoems = dailyPoemService.getRandomPoems(count)
        return ResponseEntity.ok(randomPoems)
    }
}