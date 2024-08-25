package ru.psuti.conf.news.entity

import jakarta.persistence.*

@Entity
@Table(name = "news")
data class News(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
                val title: String,
                val content: String,
                @ManyToOne
                @JoinColumn(name = "category_id")
                val category: NewsCategory)