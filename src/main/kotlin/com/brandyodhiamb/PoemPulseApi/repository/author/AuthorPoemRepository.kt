package com.brandyodhiamb.PoemPulseApi.repository.author

import com.brandyodhiamb.PoemPulseApi.models.entity.author.AuthorPoemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface AuthorPoemRepository:JpaRepository<AuthorPoemEntity,Long> {

    fun findByAuthor(author: String): List<AuthorPoemEntity>

    @Query(value = "SELECT * FROM author_poems_table ORDER BY RANDOM() LIMIT :count", nativeQuery = true)
    fun findRandomPoems(@Param("count") count: Int): List<AuthorPoemEntity>
}