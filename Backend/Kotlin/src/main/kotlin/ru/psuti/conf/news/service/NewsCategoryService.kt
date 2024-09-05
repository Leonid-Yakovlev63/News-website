package ru.psuti.conf.news.service

import org.springframework.stereotype.Service
import ru.psuti.conf.news.entity.NewsCategory
import ru.psuti.conf.news.repository.NewsCategoryRepository

@Service
class NewsCategoryService(private val newsCategoryRepository: NewsCategoryRepository) {
    fun findNewsCategories() = newsCategoryRepository.findAll()
    fun findNewsCategoryById(id: Long) = newsCategoryRepository.findById(id)
    fun saveNewsCategory(newsCategory: NewsCategory) = newsCategoryRepository.save(newsCategory)
}