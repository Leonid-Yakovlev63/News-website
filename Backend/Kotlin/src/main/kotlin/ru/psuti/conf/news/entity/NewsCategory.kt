package ru.psuti.conf.news.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "news_category")
data class NewsCategory(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
                        val name : String,
                        val color: String)