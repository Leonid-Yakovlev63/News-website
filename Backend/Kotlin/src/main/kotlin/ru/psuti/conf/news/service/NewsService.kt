package ru.psuti.conf.news.service

import org.springframework.stereotype.Service
import ru.psuti.conf.news.entity.News
import ru.psuti.conf.news.entity.NewsCategory
import ru.psuti.conf.news.repository.NewsRepository
import java.util.*

@Service
class NewsService(private val newsRepository: NewsRepository) {
    fun findNews():Iterable<News> = newsRepository.findAll()
    fun findNewsById(id: Long):Optional<News> = newsRepository.findById(id)
    fun findNewsByTitle(title: String):Iterable<News> = newsRepository.findByTitleContainingIgnoreCase(title)
    fun saveNews(news: News): News = newsRepository.save(news)

    fun updateNews(id: Long, updatedNews: News): Optional<News> {
        val existingNews = newsRepository.findById(id)
        return if (existingNews.isPresent) {
            val newsToUpdate = existingNews.get().copy(
                title = updatedNews.title,
                content = updatedNews.content,
                category = updatedNews.category
            )
            Optional.of(newsRepository.save(newsToUpdate))
        } else {
            Optional.empty()
        }
    }
    fun deleteNews(id: Long): Boolean {
        val existingNews = newsRepository.findById(id)
        return if (existingNews.isPresent) {
            newsRepository.delete(existingNews.get())
            true
        } else {
            false
        }
    }
}