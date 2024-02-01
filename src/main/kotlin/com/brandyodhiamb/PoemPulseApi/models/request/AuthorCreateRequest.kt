package com.brandyodhiamb.PoemPulseApi.models.request

import jakarta.validation.constraints.NotBlank

data class AuthorCreateRequest(
    @NotBlank(message = "Author name should not be provided")
    val name:String
)
