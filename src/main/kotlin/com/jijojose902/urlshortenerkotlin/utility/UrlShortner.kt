package com.jijojose902.urlshortenerkotlin.utility

import cn.ipokerface.snowflake.SnowflakeIdGenerator
import com.google.common.hash.Hashing
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

private const val SNOW_FLAKE_WORKER_ID: Long = 11
private const val SNOW_FLAKE_DATA_CENTRE_ID: Long = 23
private const val SHORT_URL_SIZE = 32


@Component
class UrlShortner {
    /**
     * To generate short url code we are using the combination of snowflake id and apache commons random string
     * Snow flake id will never repeat because it's a combination of current time stamp with other 3 or 4 parameters
     * finally taking a hashed value using google guava library of the combined snowflake and random string
     */
    fun generateShortCode(): String {
        var uniquecode = RandomStringUtils.random(SHORT_URL_SIZE, true, true)
            .plus(SnowflakeIdGenerator(SNOW_FLAKE_WORKER_ID, SNOW_FLAKE_DATA_CENTRE_ID).nextId().toString())
        return Hashing.murmur3_32().hashString(uniquecode, StandardCharsets.UTF_8).toString()
    }
}