package com.jijojose902.urlshortenerkotlin.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.jijojose902.urlshortenerkotlin.dto.UrlRequest
import com.jijojose902.urlshortenerkotlin.exception.InvalidUrlException
import com.jijojose902.urlshortenerkotlin.model.URL
import com.jijojose902.urlshortenerkotlin.serviceImpl.ShortUrlServiceImpl
import com.jijojose902.urlshortenerkotlin.utility.MockitoHelper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.time.LocalDateTime

@SpringBootTest
class ShortUrlControllerTest {
    @Mock
    lateinit var mockUrlService: ShortUrlServiceImpl
    private lateinit var mockMvc: MockMvc
    private lateinit var mapper: ObjectMapper

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ShortUrlController(mockUrlService)).build();
        mapper = ObjectMapper()
    }

    @Test
    @DisplayName("POST Api Generate Short Url /generate - Success")
    fun generateShortUrlSuccessRequest() {
        var request = UrlRequest()
        var url = URL(
            "jUtAuQn",
            "http://localhost:8080/jUtAuQn",
            "https://www.google.co.in/",
            true,
            LocalDateTime.now(),
            LocalDateTime.now()
        )
        Mockito.`when`(mockUrlService.saveUrlInfo(MockitoHelper.anyObject()))
            .thenReturn(url)
        var mvcResult: MvcResult = mockMvc.perform(
            post("/generate").contentType("application/json").content(mapper.writeValueAsString(request))
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn()
        assertEquals(200, mvcResult.getResponse().getStatus())
    }

    @Test
    @DisplayName("POST Api Generate Short Url /generate - Throws Exception")
    fun generateShortUrlThrowException() {
        var request = UrlRequest()
        Mockito.`when`(mockUrlService.saveUrlInfo(MockitoHelper.anyObject()))
            .thenThrow(InvalidUrlException())
        Assertions.assertThrows(Exception::class.java) {
            mockMvc.perform(
                post("/generate").contentType("application/json").content(mapper.writeValueAsString(request))
            ).andExpect(MockMvcResultMatchers.status().isBadRequest).andReturn()
        }
    }
}