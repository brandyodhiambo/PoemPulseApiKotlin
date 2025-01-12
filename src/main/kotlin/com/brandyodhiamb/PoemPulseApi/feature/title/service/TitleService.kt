package com.brandyodhiamb.PoemPulseApi.feature.title.service

import com.brandyodhiamb.PoemPulseApi.exception.BadRequestException
import com.brandyodhiamb.PoemPulseApi.exception.NotFoundException
import com.brandyodhiamb.PoemPulseApi.feature.title.models.dto.TitleDto
import com.brandyodhiamb.PoemPulseApi.feature.title.models.entity.TitleEntity
import com.brandyodhiamb.PoemPulseApi.feature.title.models.model.Title
import com.brandyodhiamb.PoemPulseApi.feature.title.repository.TitleRepository

import org.springframework.stereotype.Service
import org.springframework.util.ReflectionUtils
import java.lang.reflect.Field
import java.util.stream.Collectors
import kotlin.reflect.full.memberProperties

@Service
class TitleService(
    private  val titleRepository: TitleRepository
) {
    fun getAllTitles():List<TitleDto>{
       val dbTitles =  titleRepository.findAll().stream().map(this::convertTitleToTitleDto).collect(Collectors.toList())
        return dbTitles
    }

    fun getTitleById(id:Long): TitleDto {
        checkForTitleId(id)
        val title = titleRepository.findTitleById(id)
        return convertTitleToTitleDto(title)
    }

    fun createTitle(createRequest: Title): TitleDto {
        if(titleRepository.doesTitleExist(createRequest.title)){
            throw BadRequestException("There is already a title with similar name ${createRequest.title}")
        }
        val titleEntity = TitleEntity()
        assignTitleToEntity(titleEntity,createRequest)
        val savedTitle = titleRepository.save(titleEntity)
        return convertTitleToTitleDto(savedTitle)
    }


    fun updateTitle(id: Long, updateRequest: Title): TitleDto {
        checkForTitleId(id)
        val existingtitle: TitleEntity = titleRepository.findTitleById(id)

        for (prop in Title::class.memberProperties) {
            if (prop.get(updateRequest) != null) {
                val field: Field? = ReflectionUtils.findField(TitleEntity::class.java, prop.name)
                field?.let {
                    it.isAccessible = true
                    ReflectionUtils.setField(it, existingtitle, prop.get(updateRequest))
                }
            }
        }

        val savedtitle = titleRepository.save(existingtitle)
        return convertTitleToTitleDto(savedtitle)
    }

    fun deleteTitle(id: Long): String {
        checkForTitleId(id)
        titleRepository.deleteById(id)
        return "Title with id: $id has been deleted."
    }




    //Util functions
    fun assignTitleToEntity(titleEntity: TitleEntity, title: Title){
        titleEntity.title = title.title
    }
    fun convertTitleToTitleDto(titleEntity: TitleEntity): TitleDto {
        return TitleDto(
            title = titleEntity.title
        )
    }
    fun checkForTitleId(id:Long){
        if(titleRepository.existsById(id).not()){
            throw NotFoundException("Title with the id number $id does not exist")
        }
    }
}