package com.brandyodhiamb.PoemPulseApi.feature.author.models.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank


@Entity
@Table(name = "authors_table" )
class AuthorEntity {

    @Id
    @GeneratedValue(generator = "author_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "author_sequence", sequenceName = "author_sequence", allocationSize = 1)
    val id:Long = 0

    @NotBlank
    @Column(name = "authors", nullable = true, unique = false)
    var name :String = ""
}


