package com.jijojose902.urlshortenerkotlin.integration.repository

import com.jijojose902.urlshortenerkotlin.model.URL
import org.springframework.data.jpa.repository.JpaRepository

interface TestH2JpaRepository : JpaRepository<URL, String> {
}