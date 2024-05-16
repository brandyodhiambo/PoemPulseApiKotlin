package com.brandyodhiamb.PoemPulseApi.service

import com.brandyodhiamb.PoemPulseApi.datasource.author.AuthorDataSource
import com.brandyodhiamb.PoemPulseApi.exception.BadRequestException
import com.brandyodhiamb.PoemPulseApi.models.model.Author
import com.brandyodhiamb.PoemPulseApi.models.dto.AuthorDto
import com.brandyodhiamb.PoemPulseApi.models.entity.author.AuthorEntity
import com.brandyodhiamb.PoemPulseApi.exception.NotFoundException
import com.brandyodhiamb.PoemPulseApi.models.request.AuthorCreateRequest
import com.brandyodhiamb.PoemPulseApi.models.updates.AuthorUpdateRequest
import com.brandyodhiamb.PoemPulseApi.repository.AuthorRepository
import java.lang.reflect.Field
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.util.ReflectionUtils
import java.util.stream.Collectors
import kotlin.reflect.full.memberProperties

@Service
class AuthorService(
    private val authorRepository: AuthorRepository,
    @Qualifier("author_mock") private val authorDataSource: AuthorDataSource
) {
    fun getAllAuthors(): List<AuthorDto> {
        val dbAuthor =
            authorRepository.findAll().stream().map(this::convertEntityToAuthorDto).collect(Collectors.toList())
        val networkAuthor = authorDataSource.getAuthors().map { convertAuthorToAuthorDto(it) }
        return dbAuthor + networkAuthor
    }

    fun getAuthorById(id: Long): AuthorDto {
        checkForAuthorId(id)
        val author = authorRepository.findAuthorById(id)
        return convertEntityToAuthorDto(author)
    }

    fun createAuthor(createRequest: AuthorCreateRequest): AuthorDto {
        if (authorRepository.doesAuthorExist(createRequest.name)) {
            throw BadRequestException("There is already an author with the name :${createRequest.name}")
        }

        val author = AuthorEntity()
        assignAuthorToEntity(author, createRequest)
        val savedAuthor = authorRepository.save(author)
        return convertEntityToAuthorDto(savedAuthor)
    }

    fun updateAuthor(id: Long, updateRequest: AuthorUpdateRequest): AuthorDto {
        checkForAuthorId(id)
        val existingAuthor: AuthorEntity = authorRepository.findAuthorById(id)

        for (prop in AuthorUpdateRequest::class.memberProperties) {
            if (prop.get(updateRequest) != null) {
                val field: Field? = ReflectionUtils.findField(AuthorEntity::class.java, prop.name)
                field?.let {
                    it.isAccessible = true
                    ReflectionUtils.setField(it, existingAuthor, prop.get(updateRequest))
                }
            }
        }

        val savedAuthor = authorRepository.save(existingAuthor)
        return convertEntityToAuthorDto(savedAuthor)
    }


    fun deleteAuthor(id: Long): String {
        checkForAuthorId(id)
        authorRepository.deleteById(id)
        return "Author with id: $id has been deleted."
    }




    // Util Functions
    fun assignAuthorToEntity(authorEntity: AuthorEntity, authorCreateRequest: AuthorCreateRequest) {
        authorEntity.name = authorCreateRequest.name
    }


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

    private fun checkForAuthorId(id: Long) {
        if (authorRepository.existsById(id).not()) {
            throw NotFoundException("Author with id number $id does not exist")
        }
    }
}