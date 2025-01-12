package com.brandyodhiamb.PoemPulseApi.feature.author.datasource.author

import com.brandyodhiamb.PoemPulseApi.feature.author.models.model.Author

interface AuthorDataSource {
    fun getAuthors():List<Author>
     fun getAuthorById(id:Long): Author
}