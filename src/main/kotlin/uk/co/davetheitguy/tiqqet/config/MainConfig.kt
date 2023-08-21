package uk.co.davetheitguy.tiqqet.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class MainConfig {
    @Bean
    fun encoder(): PasswordEncoder = BCryptPasswordEncoder(11)
}
