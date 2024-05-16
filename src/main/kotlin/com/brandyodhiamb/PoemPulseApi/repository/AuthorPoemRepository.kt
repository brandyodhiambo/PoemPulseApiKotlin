package com.brandyodhiamb.PoemPulseApi.repository

import com.brandyodhiamb.PoemPulseApi.models.entity.author.AuthorPoemEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorPoemRepository:JpaRepository<AuthorPoemEntity,Long> {

    fun findByAuthor(author: String): List<AuthorPoemEntity>
}