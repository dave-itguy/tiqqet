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
            .build())
    }

    @Bean
    @ConditionalOnMissingBean(AuthenticationEventPublisher::class)
    fun authenticationEventPublisher(delegate: ApplicationEventPublisher): AuthenticationEventPublisher {
        return DefaultAuthenticationEventPublisher(delegate)
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            authorizeRequests {
                authorize("/actuator/**", permitAll)
                authorize("/", authenticated)
            }
            formLogin {
                permitAll()
            }
        }

        return http.build()
    }
}
