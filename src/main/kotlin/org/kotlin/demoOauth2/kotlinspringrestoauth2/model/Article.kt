package org.kotlin.demoOauth2.kotlinspringrestoauth2.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity data class Article (
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        @get: NotBlank
        val title: String = "",

        @get: NotBlank
        val content: String = ""
)