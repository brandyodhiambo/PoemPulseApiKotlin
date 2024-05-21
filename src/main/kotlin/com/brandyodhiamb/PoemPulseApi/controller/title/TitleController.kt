package com.brandyodhiamb.PoemPulseApi.controller.title

import com.brandyodhiamb.PoemPulseApi.models.dto.title.TitleDto
import com.brandyodhiamb.PoemPulseApi.models.model.title.Title
import com.brandyodhiamb.PoemPulseApi.service.title.TitleService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/")
class TitleController(private val titleService: TitleService) {
    @GetMapping("titles")
    fun getAlltitles(): ResponseEntity<Map<String, List<String>>> {
        val titles = titleService.getAllTitles().map { it.title }
        val response = mapOf("titles" to titles)
        return ResponseEntity.ok(response)
    }


    @GetMapping("title/{id}")
    fun gettitleById(@PathVariable id:Long): ResponseEntity<TitleDto> =
        ResponseEntity(titleService.getTitleById(id), HttpStatus.OK)

    @PostMapping("create_title")
    fun createtitle(
        @Valid @RequestBody createRequest: Title
    ): ResponseEntity<TitleDto> = ResponseEntity(titleService.createTitle(createRequest), HttpStatus.OK)

    @PatchMapping("update-title/{id}")
    fun updatetitle(
        @PathVariable id: Long,
        @Valid @RequestBody updateRequest: Title
    ): ResponseEntity<TitleDto> = ResponseEntity(titleService.updateTitle(id, updateRequest), HttpStatus.OK)

    @DeleteMapping("delete-title/{id}")
    fun deletetitle(@PathVariable id: Long): ResponseEntity<String> =
        ResponseEntity(titleService.deleteTitle(id), HttpStatus.OK)
}