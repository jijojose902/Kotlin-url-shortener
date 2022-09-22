package com.jijojose902.urlshortenerkotlin.service

import com.jijojose902.urlshortenerkotlin.dto.UrlRequest
import com.jijojose902.urlshortenerkotlin.model.URL

interface ShortUrlService {
    fun saveUrlInfo(originalUrl: UrlRequest): URL
    fun getOriginalUrlById(id: String): URL?
}