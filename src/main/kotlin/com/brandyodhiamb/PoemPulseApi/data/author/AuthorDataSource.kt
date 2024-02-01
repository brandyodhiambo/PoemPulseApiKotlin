package com.brandyodhiamb.PoemPulseApi.data.author

import com.brandyodhiamb.PoemPulseApi.data.author.model.Author

interface AuthorDataSource {
    fun getAuthors():List<Author>
     fun getAuthorById(id:Long): Author
}