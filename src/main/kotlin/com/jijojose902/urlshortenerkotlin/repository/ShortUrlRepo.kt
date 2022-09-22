package com.jijojose902.urlshortenerkotlin.repository

import com.jijojose902.urlshortenerkotlin.model.URL
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShortUrlRepo : JpaRepository<URL, String> {


}