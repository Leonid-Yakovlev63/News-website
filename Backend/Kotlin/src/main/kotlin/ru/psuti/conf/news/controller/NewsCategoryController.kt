package ru.psuti.conf.news.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.psuti.conf.news.entity.NewsCategory
import ru.psuti.conf.news.service.NewsCategoryService


@RestController()
@RequestMapping("/news-category")
class NewsCategoryController(private val newsCategoryService: NewsCategoryService) {
    @PostMapping
    fun saveNewsCategory(@RequestBody newsCategory: NewsCategory) = newsCategoryService.saveNewsCategory(newsCategory)
}