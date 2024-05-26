package uk.co.davetheitguy.tiqqet.security.jwt.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import uk.co.davetheitguy.tiqqet.security.jwt.services.JwtUtils
import uk.co.davetheitguy.tiqqet.security.jwt.services.implementations.JwtUtilsImpl

@Configuration
@EnableConfigurationProperties(JwtConfig::class)
class JwtTokenAuthorizationConfig {
    @Bean
    fun jwtUtils(config: JwtConfig): JwtUtils {
        return JwtUtilsImpl(config)
    }
}
