package com.brandyodhiamb.PoemPulseApi.controller

import com.brandyodhiamb.PoemPulseApi.models.dto.AuthorPoemDto
import com.brandyodhiamb.PoemPulseApi.models.model.AuthorPoem
import com.brandyodhiamb.PoemPulseApi.service.AuthorPoemService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/author/poem")
class AuthorPoemController(private val authorPoemService: AuthorPoemService) {
    @GetMapping("/{authorName}")
    fun getAuthorPoem(@PathVariable authorName: String): ResponseEntity<List<AuthorPoemDto>> {
        val poems = authorPoemService.getAuthorPoem(authorName)
        return ResponseEntity(poems,HttpStatus.OK)
    }

    @PostMapping("create_author_poem")
    fun createAuthorPoem(
        @Valid @RequestBody authorPoem: AuthorPoem
    ):ResponseEntity<AuthorPoemDto> = ResponseEntity(authorPoemService.createAuthorPoem(authorPoem),HttpStatus.OK)

    @PatchMapping("update-author-poem/{id}")
    fun updateAuthorPoem(
        @PathVariable id: Long,
        @Valid @RequestBody authorPoem: AuthorPoem
    ): ResponseEntity<AuthorPoemDto> = ResponseEntity(authorPoemService.updateAuthorPoem(id, authorPoem), HttpStatus.OK)

    @DeleteMapping("delete-author-poem/{id}")
    fun deleteAuthorPoem(@PathVariable id: Long): ResponseEntity<String> =
        ResponseEntity(authorPoemService.deleteAuthorPoem(id), HttpStatus.OK)

    @GetMapping("/random")
    fun getRandomPoems(@RequestParam count: Int): ResponseEntity<List<AuthorPoemDto>> {
        val randomPoems = authorPoemService.getRandomPoems(count)
        return ResponseEntity.ok(randomPoems)
    }
}