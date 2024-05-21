package com.brandyodhiamb.PoemPulseApi.models.entity.title

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank


@Entity
@Table(name = "title_table")
class TitleEntity {
    @Id
    @GeneratedValue(generator = "title_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "title_sequence", sequenceName = "title_sequence", allocationSize = 1)
    val id:Long = 0

    @NotBlank
    @Column(name = "titles", nullable = true, unique = false)
    var title:String = ""
}