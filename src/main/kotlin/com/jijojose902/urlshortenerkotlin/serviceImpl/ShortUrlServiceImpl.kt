package com.jijojose902.urlshortenerkotlin.serviceImpl

import com.jijojose902.urlshortenerkotlin.dto.UrlRequest
import com.jijojose902.urlshortenerkotlin.exception.InvalidUrlException
import com.jijojose902.urlshortenerkotlin.model.URL
import com.jijojose902.urlshortenerkotlin.repository.ShortUrlRepo
import com.jijojose902.urlshortenerkotlin.service.ShortUrlService
import com.jijojose902.urlshortenerkotlin.utility.UrlShortner
import mu.KotlinLogging
import org.apache.commons.validator.UrlValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.Cacheable
import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneOffset

private val logger = KotlinLogging.logger {}

@Service
class ShortUrlServiceImpl : ShortUrlService {
    @Autowired
    lateinit var shortUrlRepo: ShortUrlRepo

    @Autowired
    lateinit var srlShortner: UrlShortner

    @Value("\${service.url}")
    lateinit var serviceUrl: String

    override fun saveUrlInfo(originalUrl: UrlRequest): URL {
        if (!UrlValidator().isValid(originalUrl.longUrl)) throw InvalidUrlException()
        var shortCode = srlShortner.generateShortCode()
        var serviceURL = serviceUrl.plus(shortCode)
        var url = URL(shortCode, serviceURL, originalUrl.longUrl, true, LocalDateTime.now(ZoneOffset.UTC), null)
        logger.info { "Saving new URL request :: ${url}" }
        return shortUrlRepo.save(url)
    }

    @Cacheable(value = ["URLS"], key = "#id", unless = "#result == null")
    override fun getOriginalUrlById(id: String): URL? {
        logger.info { "Calling get request for id :: ${id}" }
        var urlResponse: URL? = null
        try {
            urlResponse = shortUrlRepo.getReferenceById(id);
        } catch (ex: DataAccessException) {
            logger.error { "Exception occurred while getting URL entity by id :: ${ex.message}" }
        }
        return urlResponse
    }
}