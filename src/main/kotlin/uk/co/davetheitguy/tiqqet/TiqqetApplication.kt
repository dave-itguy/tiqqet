package uk.co.davetheitguy.tiqqet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain

@SpringBootApplication
class TiqqetApplication

fun main(args: Array<String>) {
    runApplication<TiqqetApplication>(*args)
}

@Configuration
class TiqqetSecurityConfig {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            httpBasic {  }
            authorizeHttpRequests {
                authorize(anyRequest, permitAll)
            }
            csrf {
                disable()
            }
        }
        return http.build()
    }
}