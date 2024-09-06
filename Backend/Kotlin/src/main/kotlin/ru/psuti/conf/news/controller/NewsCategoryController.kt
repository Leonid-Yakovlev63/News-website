package ru.psuti.conf.news.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.psuti.conf.news.entity.NewsCategory
import ru.psuti.conf.news.service.NewsCategoryService
import java.util.*

@RestController
@RequestMapping("/news-category")
class NewsCategoryController(private val newsCategoryService: NewsCategoryService) {

    @GetMapping
    fun getAllCategories(): ResponseEntity<Iterable<NewsCategory>> {
        return ResponseEntity.ok(newsCategoryService.findNewsCategories())
    }

    @GetMapping("/{id}")
    fun getCategoryById(@PathVariable id: Long): ResponseEntity<NewsCategory> {
        val category = newsCategoryService.findNewsCategoryById(id)
        return category.map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())
    }

    @PostMapping
    fun saveNewsCategory(@RequestBody newsCategory: NewsCategory): ResponseEntity<NewsCategory> {
        val savedCategory = newsCategoryService.saveNewsCategory(newsCategory)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory)
    }

    @PutMapping("/{id}")
    fun updateNewsCategory(@PathVariable id: Long, @RequestBody updatedCategory: NewsCategory): ResponseEntity<NewsCategory> {
        val updated = newsCategoryService.updateNewsCategory(id, updatedCategory)
        return updated.map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())
    }

    @PatchMapping("/{id}")
    fun patchNewsCategory(@PathVariable id: Long, @RequestBody patchData: Map<String, String?>): ResponseEntity<NewsCategory> {
        val name = patchData["name"]
        val color = patchData["color"]
        val patched = newsCategoryService.patchNewsCategory(id, name, color)
        return patched.map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())
    }
    @DeleteMapping("/{id}")
    fun deleteNewsCategory(@PathVariable id: Long): ResponseEntity<Void> {
        return if (newsCategoryService.deleteNewsCategory(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
