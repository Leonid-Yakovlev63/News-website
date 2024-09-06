package ru.psuti.conf.news.service

import org.springframework.stereotype.Service
import ru.psuti.conf.news.entity.NewsCategory
import ru.psuti.conf.news.repository.NewsCategoryRepository
import java.util.*

@Service
class NewsCategoryService(private val newsCategoryRepository: NewsCategoryRepository) {

    fun findNewsCategories(): Iterable<NewsCategory> = newsCategoryRepository.findAll()

    fun findNewsCategoryById(id: Long): Optional<NewsCategory> = newsCategoryRepository.findById(id)

    fun saveNewsCategory(newsCategory: NewsCategory): NewsCategory = newsCategoryRepository.save(newsCategory)

    fun updateNewsCategory(id: Long, updatedCategory: NewsCategory): Optional<NewsCategory> {
        val existingCategory = newsCategoryRepository.findById(id)
        return if (existingCategory.isPresent) {
            val categoryToUpdate = existingCategory.get().copy(
                name = updatedCategory.name,
                color = updatedCategory.color
            )
            Optional.of(newsCategoryRepository.save(categoryToUpdate))
        } else {
            Optional.empty()
        }
    }

    fun patchNewsCategory(id: Long, name: String?, color: String?): Optional<NewsCategory> {
        val existingCategory = newsCategoryRepository.findById(id)
        return if (existingCategory.isPresent) {
            val categoryToPatch = existingCategory.get().copy(
                name = name ?: existingCategory.get().name,
                color = color ?: existingCategory.get().color
            )
            Optional.of(newsCategoryRepository.save(categoryToPatch))
        } else {
            Optional.empty()
        }
    }

    fun deleteNewsCategory(id: Long): Boolean {
        val existingCategory = newsCategoryRepository.findById(id)
        return if (existingCategory.isPresent) {
            newsCategoryRepository.delete(existingCategory.get())
            true
        } else {
            false
        }
    }
}