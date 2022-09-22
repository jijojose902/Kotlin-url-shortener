package com.jijojose902.urlshortenerkotlin.model

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity

data class URL(
    @Id
    var id: String?,
    @Column(nullable = false) // we can use @NotBlank also
    var shortUrl: String?,
    @Column(nullable = false)
    var longUrl: String?,
    @Column(nullable = false)
    var isActive: Boolean = true,
    @Column(nullable = false)
    var createdDate: LocalDateTime,
    @Column(nullable = true)
    var expiryDate: LocalDateTime?
) : Serializable

