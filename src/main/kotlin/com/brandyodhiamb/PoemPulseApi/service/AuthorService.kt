package com.brandyodhiamb.PoemPulseApi.service

import com.brandyodhiamb.PoemPulseApi.data.author.AuthorDataSource
import com.brandyodhiamb.PoemPulseApi.data.author.model.Author
import com.brandyodhiamb.PoemPulseApi.models.dto.AuthorDto
import com.brandyodhiamb.PoemPulseApi.models.entity.AuthorEntity
import com.brandyodhiamb.PoemPulseApi.exception.NotFoundException
import com.brandyodhiamb.PoemPulseApi.repository.AuthorRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class AuthorService(
    private val authorRepository: AuthorRepository,
    @Qualifier("author_mock") private val authorDataSource: AuthorDataSource
) {
    fun getAllAuthors(): List<AuthorDto>{
        val dbAuthor = authorRepository.findAll().stream().map ( this::convertEntityToAuthorDto).collect(Collectors.toList())
        val networkAuthor = authorDataSource.getAuthors().map { convertAuthorToAuthorDto(it) }
        return dbAuthor + networkAuthor
    }
    fun getAuthorById(id: Long): AuthorDto {
        checkForAuthorId(id)
        val author = authorRepository.findAuthorById(id)
        return convertEntityToAuthorDto(author)
    }



    // Util Functions
    private fun convertEntityToAuthorDto(authorEntity: AuthorEntity): AuthorDto {
        return AuthorDto(
            name = authorEntity.name
        )
    }

    private fun convertAuthorToAuthorDto(author: Author): AuthorDto {
        return AuthorDto(
            name = author.name
        )
    }

    private fun checkForAuthorId(id:Long){
        if(authorRepository.existsById(id).not()){
            throw NotFoundException("Author with id number $id does not exist")
        }
    }
}