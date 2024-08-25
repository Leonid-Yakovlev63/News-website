package ru.psuti.conf.news.controller

import org.springframework.web.bind.annotation.*
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
}