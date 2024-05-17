package com.brandyodhiamb.PoemPulseApi.service

import com.brandyodhiamb.PoemPulseApi.models.dto.AuthorPoemDto
import com.brandyodhiamb.PoemPulseApi.models.entity.author.AuthorPoemEntity
import com.brandyodhiamb.PoemPulseApi.models.model.AuthorPoem
import com.brandyodhiamb.PoemPulseApi.repository.AuthorPoemRepository
import com.brandyodhiamb.PoemPulseApi.repository.AuthorRepository
import org.springframework.stereotype.Service

@Service
class AuthorPoemService(
    private val authorPoemRepository: AuthorPoemRepository,
    private val authorRepository: AuthorRepository
) {

    fun createAuthorPoem(poem: AuthorPoem): AuthorPoemDto {
        authorRepository.findAuthorByName(poem.author)
            ?: throw RuntimeException("Author not found with name ${poem.author}")
        val poemEntity = AuthorPoemEntity()
        assignAuthorPoemToAuthorPoemEntity(poem,poemEntity)
        val savedPoemEntity = authorPoemRepository.save(poemEntity)
        return convertAuthorPoemEntityToAuthorPoemDto(savedPoemEntity)
    }


    fun updateAuthorPoem(poemId: Long, updatedPoem: AuthorPoem): AuthorPoemDto {
        val existingPoemEntity = authorPoemRepository.findById(poemId)
            .orElseThrow { NoSuchElementException("Poem with id $poemId not found") }
        assignAuthorPoemToAuthorPoemEntity(updatedPoem,existingPoemEntity)
        val updatedPoemEntity = authorPoemRepository.save(existingPoemEntity)
        return convertAuthorPoemEntityToAuthorPoemDto(updatedPoemEntity)
    }


    fun deleteAuthorPoem(poemId: Long): String {
        if (!authorPoemRepository.existsById(poemId)) {
            throw NoSuchElementException("Poem with id $poemId not found")
        }
        authorPoemRepository.deleteById(poemId)

        return "Poem with id: $poemId has been deleted"
    }

    fun getAuthorPoem(author: String): List<AuthorPoemDto> {
        val poemEntities = authorPoemRepository.findByAuthor(author)
        return poemEntities.map { entity ->
            convertAuthorPoemEntityToAuthorPoemDto(entity)
        }
    }

    //Util Functions
    fun assignAuthorPoemToAuthorPoemEntity(authorPoem: AuthorPoem, authorPoemEntity: AuthorPoemEntity) {
        authorPoemEntity.title = authorPoem.title
        authorPoemEntity.author = authorPoem.author
        authorPoemEntity.lines = authorPoem.lines.joinToString(separator = "\n")
        authorPoemEntity.lineCount = authorPoem.lineCount
        authorPoemEntity.authorEntity = authorRepository.findAuthorByName(authorPoem.author)
            ?: throw RuntimeException("Author not found with name ${authorPoem.author}")
    }

    private fun convertAuthorPoemEntityToAuthorPoemDto(authorPoemEntity: AuthorPoemEntity): AuthorPoemDto {
        return AuthorPoemDto(
            title = authorPoemEntity.title,
            author = authorPoemEntity.author,
            lines = authorPoemEntity.lines.split("\n"),
            lineCount = authorPoemEntity.lineCount
        )
    }
}