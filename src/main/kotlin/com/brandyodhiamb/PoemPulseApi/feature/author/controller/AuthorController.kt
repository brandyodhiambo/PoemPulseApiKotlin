package com.brandyodhiamb.PoemPulseApi.feature.author.controller

import com.brandyodhiamb.PoemPulseApi.feature.author.models.dto.AuthorDto
import com.brandyodhiamb.PoemPulseApi.feature.author.models.model.Author
import com.brandyodhiamb.PoemPulseApi.feature.author.service.AuthorService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/")
class AuthorController(private val authorService: AuthorService) {

    @GetMapping("authors")
    fun getAllAuthors(): ResponseEntity<Map<String, List<String>>> {
        val authors = authorService.getAllAuthors().map { it.name }
        val response = mapOf("authors" to authors)
        return ResponseEntity.ok(response)
    }

    @PostMapping("create_author")
    fun createAuthor(
        @Valid @RequestBody createRequest: Author
    ): ResponseEntity<AuthorDto> = ResponseEntity(authorService.createAuthor(createRequest),HttpStatus.OK)

    @PatchMapping("update-author/{id}")
    fun updateAuthor(
        @PathVariable id: Long,
        @Valid @RequestBody updateRequest: Author
    ): ResponseEntity<AuthorDto> = ResponseEntity(authorService.updateAuthor(id, updateRequest), HttpStatus.OK)

    @DeleteMapping("delete-author/{id}")
    fun deleteAuthor(@PathVariable id: Long): ResponseEntity<String> =
        ResponseEntity(authorService.deleteAuthor(id), HttpStatus.OK)
}