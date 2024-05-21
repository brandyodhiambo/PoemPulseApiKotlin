package com.brandyodhiamb.PoemPulseApi.repository.author

import com.brandyodhiamb.PoemPulseApi.models.entity.author.AuthorEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AuthorRepository:JpaRepository<AuthorEntity,Long> {

    fun findAuthorById(id:Long): AuthorEntity

    fun findAuthorByName(name:String): AuthorEntity?



    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END FROM AuthorEntity a WHERE a.name = ?1")
    fun doesAuthorExist(author: String): Boolean

}