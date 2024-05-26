package uk.co.davetheitguy.tiqqet.security.jwt.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("jwt")
class JwtConfig {
    var audience: String? = null
    var secret: String? = null
    var expiration: Long = 0L
    var issuer: String? = null
    var prefix: String? = null
    var header: String? = null
}
