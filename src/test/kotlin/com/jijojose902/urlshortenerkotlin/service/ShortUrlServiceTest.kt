package com.jijojose902.urlshortenerkotlin.service

import com.jijojose902.urlshortenerkotlin.model.URL
import com.jijojose902.urlshortenerkotlin.repository.ShortUrlRepo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import javax.persistence.EntityNotFoundException


@SpringBootTest
class ShortUrlServiceTest {
    @Mock
    lateinit var mockUrlRepo: ShortUrlRepo

    @Test
    @DisplayName("Test FindUrlById - Success")
    fun testGetUrlByIdSuccessRequest() {
        var url = URL(
            "jUtAuQn",
            "http://localhost:8080/jUtAuQn",
            "https://www.google.co.in/",
            true,
            LocalDateTime.now(),
            LocalDateTime.now()
        )
        `when`(mockUrlRepo.getReferenceById(anyString()))
            .thenReturn(url)
        var response = mockUrlRepo.getReferenceById(anyString())
        Assertions.assertEquals(url, response)
    }

    @Test
    @DisplayName("Save URL Request - Success")
    fun testSaveUrlRequestSuccessRequest() {
        var url = URL(
            "jUtAuQn",
            "http://localhost:8080/jUtAuQn",
            "https://www.google.co.in/",
            true,
            LocalDateTime.now(),
            LocalDateTime.now()
        )
        `when`(mockUrlRepo.save(any()))
            .thenReturn(url)
        var response = mockUrlRepo.save(url)
        Assertions.assertEquals(url, response)
    }

    @Test
    @DisplayName("Test FindUrlById - throws EntityNotFoundException")
    fun testGetUrlByIdThrowsDataAccessException() {
        `when`(mockUrlRepo.getReferenceById(anyString()))
            .thenThrow(EntityNotFoundException())
        assertThrows<EntityNotFoundException> {
            mockUrlRepo.getReferenceById(anyString())
        }
    }
}