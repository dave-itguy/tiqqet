package uk.co.davetheitguy.tiqqet.security.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationEventPublisher
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import uk.co.davetheitguy.tiqqet.security.jwt.config.JwtConfig
import uk.co.davetheitguy.tiqqet.security.jwt.filter.JwtTokenAuthenticationFilter
import uk.co.davetheitguy.tiqqet.security.jwt.services.JwtUtils

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    @ConditionalOnMissingBean(UserDetailsService::class)
    fun userDetailsService(): InMemoryUserDetailsManager {
        return InMemoryUserDetailsManager(
            User
                .withUsername("user")
                .password("{noop}password")
                .roles("ADMIN")
                .build()
        )
    }

    @Bean
    @ConditionalOnMissingBean(AuthenticationEventPublisher::class)
    fun authenticationEventPublisher(delegate: ApplicationEventPublisher): AuthenticationEventPublisher {
        return DefaultAuthenticationEventPublisher(delegate)
    }

    @Bean
    fun jwtTokenAuthenticationFilter(config: JwtConfig, utils: JwtUtils): JwtTokenAuthenticationFilter {
        return JwtTokenAuthenticationFilter(config, utils)
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity, filter: JwtTokenAuthenticationFilter): SecurityFilterChain {
        http {
            authorizeRequests {
                authorize("/actuator/**", permitAll)
                authorize("/", authenticated)
            }
            formLogin {
                permitAll()
            }
        }

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}

