package com.brandyodhiamb.PoemPulseApi.feature.author.repository

import com.brandyodhiamb.PoemPulseApi.feature.author.models.entity.AuthorPoemEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorPoemRepository:JpaRepository<AuthorPoemEntity,Long> {

    fun findByAuthor(author: String): List<AuthorPoemEntity>

}