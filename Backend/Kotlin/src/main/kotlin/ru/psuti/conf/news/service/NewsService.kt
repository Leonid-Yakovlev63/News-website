package ru.psuti.conf.news.service

import org.springframework.stereotype.Service
import ru.psuti.conf.news.repository.NewsRepository

@Service
class NewsService(private val newsRepository: NewsRepository) {
    fun findNews() = newsRepository.findAll()
    fun findNewsById(id: Long) = newsRepository.findById(id)
    fun findNewsByTitle(title: String) = newsRepository.findByTitleContainingIgnoreCase(title)
}