package uk.co.davetheitguy.tiqqet.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import javax.sql.DataSource

@Profile("release")
@Configuration
class ReleaseConfig {
    @Bean
    fun debugManager(dataSource: DataSource, passwordEncoder: PasswordEncoder): JdbcUserDetailsManager {
        return JdbcUserDetailsManager(dataSource).apply {
            if (userExists("droberts")) return@apply
            val hash = passwordEncoder.encode("Avalanche1983")
            User.withUsername("droberts").password(hash).roles("admin").build().let(::createUser)
        }
    }
}
