package com.brandyodhiamb.PoemPulseApi.feature.author.datasource.author.mock

import com.brandyodhiamb.PoemPulseApi.feature.author.datasource.author.AuthorDataSource
import com.brandyodhiamb.PoemPulseApi.feature.author.models.model.Author
import org.springframework.stereotype.Repository

@Repository("author_mock")
class MockAuthorDataSource: AuthorDataSource {
    val author = listOf(
        Author(id = 1,name = "Adam Lindsay Gordon"),
        Author(id = 2,name = "Alan Seeger"),
        Author(id = 3,name = "Ann Taylor"),
        Author(id = 4,name = "Charlotte Bronte"),
        Author(id = 5,name = "Edward Lear"),
    )
    override fun getAuthors(): List<Author> {
        return author
    }

    override fun getAuthorById(id: Long): Author {
        val autho = author.firstOrNull { it.id ==id.toInt() } ?: throw NoSuchElementException("Author with ID $id is not present")
        return autho
    }
}