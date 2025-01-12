package com.brandyodhiamb.PoemPulseApi.feature.author.datasource.author.network

import com.brandyodhiamb.PoemPulseApi.feature.author.datasource.author.AuthorDataSource
import com.brandyodhiamb.PoemPulseApi.feature.author.datasource.author.network.dto.AuthorList
import com.brandyodhiamb.PoemPulseApi.feature.author.models.model.Author
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

@Repository("author_network")
class AuthorNetworkDataSource(
    @Autowired private val restTemplate: RestTemplate
): AuthorDataSource {
    override fun getAuthors(): List<Author> {
        val response:ResponseEntity<AuthorList> =
            restTemplate.getForEntity<AuthorList>("https://poetrydb.org/author")

        return convertToAuthors(response.body?.names)?: emptyList()
    }

    override fun getAuthorById(id: Long): Author {
        TODO("Not yet implemented")
    }

    private fun convertToAuthors(authorNames: List<String>?): List<Author>? {
        return authorNames?.map { Author(name = it) }
    }
}