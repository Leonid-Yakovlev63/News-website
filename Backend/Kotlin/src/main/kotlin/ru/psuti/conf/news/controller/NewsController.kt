package ru.psuti.conf.news.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.psuti.conf.news.entity.News
import ru.psuti.conf.news.entity.NewsCategory
import ru.psuti.conf.news.service.NewsService

@CrossOrigin
@RestController()
@RequestMapping("/news")
class NewsController(private val newsService: NewsService) {
    @GetMapping
    fun findNews() = newsService.findNews()

    @GetMapping("/{id}")
    fun findNewsById(@PathVariable id : Long) = newsService.findNewsById(id)

    @GetMapping("/search")
    fun findNewsByTitle(@RequestParam title: String) = newsService.findNewsByTitle(title)
    @PostMapping
    fun saveNews(@RequestBody news: News): ResponseEntity<News> {
        val savedNews = newsService.saveNews(news)
        return ResponseEntity.ok(savedNews)
    }

    @PutMapping("/{id}")
    fun updateNews(@PathVariable id: Long, @RequestBody updatedNews: News): ResponseEntity<News> {
        return newsService.updateNews(id, updatedNews)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun deleteNews(@PathVariable id: Long): ResponseEntity<Void> {
        return if (newsService.deleteNews(id)) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}