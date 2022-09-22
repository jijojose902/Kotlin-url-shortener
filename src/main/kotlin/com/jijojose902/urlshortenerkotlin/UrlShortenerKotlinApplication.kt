package com.jijojose902.urlshortenerkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UrlShortenerKotlinApplication

fun main(args: Array<String>) {
	runApplication<UrlShortenerKotlinApplication>(*args)
}
