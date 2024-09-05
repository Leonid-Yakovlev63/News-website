package ru.psuti.conf.news.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.psuti.conf.news.entity.NewsCategory

@Repository
interface NewsCategoryRepository : CrudRepository<NewsCategory, Long> {

}