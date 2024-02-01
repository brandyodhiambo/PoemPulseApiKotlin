package com.brandyodhiamb.PoemPulseApi.repository

import com.brandyodhiamb.PoemPulseApi.models.entity.AuthorEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepository:JpaRepository<AuthorEntity,Long> {

    fun findAuthorById(id:Long): AuthorEntity

}