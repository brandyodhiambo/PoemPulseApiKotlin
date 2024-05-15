package com.brandyodhiamb.PoemPulseApi.datasource.author

import com.brandyodhiamb.PoemPulseApi.models.model.Author

interface AuthorDataSource {
    fun getAuthors():List<Author>
     fun getAuthorById(id:Long): Author
}