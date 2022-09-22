package com.jijojose902.urlshortenerkotlin.integration.controller

import com.jijojose902.urlshortenerkotlin.dto.UrlRequest
import com.jijojose902.urlshortenerkotlin.integration.repository.TestH2JpaRepository
import com.jijojose902.urlshortenerkotlin.model.URL
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.jdbc.Sql
import org.springframework.web.client.RestTemplate


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IntegrationTestController {
    @LocalServerPort
    private val port = 0
    private var baseUrl = "http://localhost"
    private var restTemplate: RestTemplate? = null

    @Autowired
    private val h2Repository: TestH2JpaRepository? = null

    @BeforeAll
    fun init() {
        restTemplate = RestTemplate()
    }

    @BeforeEach
    fun setUp() {
        baseUrl = "$baseUrl:$port/generate"
    }

    @Test
    fun generateShortUrlTest() {
        var request: UrlRequest = UrlRequest()
        request.longUrl = "https://www.google.com/"
        request.expiryDate = "21/09/2022"
        val response = restTemplate?.postForObject(baseUrl, request, URL::class.java)
        assertEquals(1, h2Repository?.findAll()?.size);
    }
}