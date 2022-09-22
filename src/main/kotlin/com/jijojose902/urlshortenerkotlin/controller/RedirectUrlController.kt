package com.jijojose902.urlshortenerkotlin.controller

import com.jijojose902.urlshortenerkotlin.service.ShortUrlService
import io.swagger.v3.oas.annotations.Hidden
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import javax.servlet.http.HttpServletResponse

@Controller
@Hidden
class RedirectUrlController(private val shortUrlService: ShortUrlService) {
    @GetMapping("/{id}")
    fun redirect(@PathVariable id: String, response: HttpServletResponse) {
        var urlResponse = shortUrlService.getOriginalUrlById(id)
        if (null == urlResponse)
            response.sendError(HttpServletResponse.SC_NOT_FOUND)
        else
            response.sendRedirect(urlResponse.longUrl)
    }
}