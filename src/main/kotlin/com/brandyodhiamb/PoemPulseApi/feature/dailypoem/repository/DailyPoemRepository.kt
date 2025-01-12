package com.brandyodhiamb.PoemPulseApi.feature.dailypoem.repository

import com.brandyodhiamb.PoemPulseApi.feature.author.models.entity.AuthorPoemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface DailyPoemRepository:JpaRepository<AuthorPoemEntity,Long> {

    @Query(value = "SELECT * FROM author_poems_table ORDER BY RANDOM() LIMIT :count", nativeQuery = true)
    fun findRandomPoems(@Param("count") count: Int): List<AuthorPoemEntity>
}