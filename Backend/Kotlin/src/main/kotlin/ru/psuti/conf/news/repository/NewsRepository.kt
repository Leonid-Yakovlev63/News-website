package ru.psuti.conf.news.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.psuti.conf.news.entity.News

@Repository
interface NewsRepository : CrudRepository<News, Long> {
    fun findByTitleContainingIgnoreCase(title: String): List<News>
}