package org.kotlin.demoOauth2.kotlinspringrestoauth2.reposityory

import org.kotlin.demoOauth2.kotlinspringrestoauth2.model.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository interface ArticleRepository : JpaRepository<Article, Long>