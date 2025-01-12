package com.brandyodhiamb.PoemPulseApi.feature.author.service

import com.brandyodhiamb.PoemPulseApi.feature.author.models.dto.AuthorPoemDto
import com.brandyodhiamb.PoemPulseApi.feature.author.models.entity.AuthorPoemEntity
import com.brandyodhiamb.PoemPulseApi.feature.author.models.model.AuthorPoem
import com.brandyodhiamb.PoemPulseApi.feature.author.repository.AuthorPoemRepository
import com.brandyodhiamb.PoemPulseApi.feature.author.repository.AuthorRepository
import com.brandyodhiamb.PoemPulseApi.utils.convertAuthorPoemEntityToAuthorPoemDto
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

    fun assignAuthorPoemToAuthorPoemEntity(authorPoem: AuthorPoem, authorPoemEntity: AuthorPoemEntity) {
        authorPoemEntity.title = authorPoem.title
        authorPoemEntity.author = authorPoem.author
        authorPoemEntity.lines = authorPoem.lines.joinToString(separator = "\n")
        authorPoemEntity.lineCount = authorPoem.lineCount
        authorPoemEntity.authorEntity = authorRepository.findAuthorByName(authorPoem.author)
            ?: throw RuntimeException("Author not found with name ${authorPoem.author}")
    }

}