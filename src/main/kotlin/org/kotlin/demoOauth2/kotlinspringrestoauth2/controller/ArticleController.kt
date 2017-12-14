package org.kotlin.demoOauth2.kotlinspringrestoauth2.controller

import org.kotlin.demoOauth2.kotlinspringrestoauth2.model.Article
import org.kotlin.demoOauth2.kotlinspringrestoauth2.reposityory.ArticleRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class ArticleController(private val articleRepository: ArticleRepository) {
    @GetMapping("/articles")
    fun getAllArticles(): List<Article> =
            articleRepository.findAll()

    @PostMapping("/articles")
    fun createNewArticle(@Valid @RequestBody article: Article): Article =
            articleRepository.save(article)

    @GetMapping("/articles/{id}")
    fun getArticleById(@PathVariable("id") articleId: Long): ResponseEntity<Article> {
        return articleRepository.findById(articleId).map {
            article -> ResponseEntity.ok(article)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/articles/{id}")
    fun updateArticleById(@PathVariable("id") articleId: Long,
                          @Valid @RequestBody newArticle: Article): ResponseEntity<Article> {
        return articleRepository.findById(articleId).map { existingArticle ->
            val updateArticle: Article = existingArticle
                    .copy(title = newArticle.title, content = newArticle.content)
            ResponseEntity.ok().body(articleRepository.save(updateArticle))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/articles/{id}")
    fun deleteArticle(@PathVariable("id") articleId: Long): ResponseEntity<Void> {
        return articleRepository.findById(articleId).map { article ->
            articleRepository.delete(article)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}
