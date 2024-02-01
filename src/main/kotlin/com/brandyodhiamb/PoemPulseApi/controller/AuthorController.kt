package com.brandyodhiamb.PoemPulseApi.controller

import com.brandyodhiamb.PoemPulseApi.models.dto.AuthorDto
import com.brandyodhiamb.PoemPulseApi.service.AuthorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class AuthorController(private val authorService: AuthorService) {

    @GetMapping("authors")
    fun getAllAuthor():ResponseEntity<List<AuthorDto>> = ResponseEntity(authorService.getAllAuthors(),HttpStatus.OK)

    @GetMapping("author/{id}")
    fun getAuthorById(@PathVariable id:Long):ResponseEntity<AuthorDto> =
        ResponseEntity(authorService.getAuthorById(id),HttpStatus.OK)
}