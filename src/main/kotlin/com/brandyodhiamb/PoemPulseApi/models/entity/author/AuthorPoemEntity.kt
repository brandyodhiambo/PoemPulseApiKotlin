package com.brandyodhiamb.PoemPulseApi.models.entity.author

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull


@Entity
@Table(name = "poems_table")
class AuthorPoemEntity {

    @Id
    @GeneratedValue(generator = "poem_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "poem_sequence", sequenceName = "poem_sequence", allocationSize = 1)
    val id: Long = 0

    @NotBlank
    @Column(name = "title", nullable = false)
    var title: String = ""

    @NotBlank
    @Column(name = "author", nullable = false)
    var author: String = ""

    @NotBlank
    @Column(name = "lines", nullable = false, columnDefinition = "TEXT")
    var lines: String = ""

    @NotNull
    @Column(name = "line_count", nullable = false)
    var lineCount: Int = 0
}
