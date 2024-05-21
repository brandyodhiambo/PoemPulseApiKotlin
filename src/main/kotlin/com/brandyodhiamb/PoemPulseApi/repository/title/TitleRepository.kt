package com.brandyodhiamb.PoemPulseApi.repository.title

import com.brandyodhiamb.PoemPulseApi.models.entity.title.TitleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TitleRepository:JpaRepository<TitleEntity,Long> {

    fun findTitleById(id:Long):TitleEntity

   // fun findTitleByName(title:String):TitleEntity?

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END FROM TitleEntity a WHERE a.title = ?1")
    fun doesTitleExist(title: String): Boolean
}