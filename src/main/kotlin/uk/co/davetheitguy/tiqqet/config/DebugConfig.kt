package uk.co.davetheitguy.tiqqet.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import uk.co.davetheitguy.tiqqet.data.entities.TodoItem
import uk.co.davetheitguy.tiqqet.services.TodoService
import javax.sql.DataSource

@Profile("debug")
@Configuration
class DebugConfig {
    @Bean
    fun getDataSource(): DataSource {
        return EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
            .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION).build()
    }

    @Bean
    fun debugManager(dataSource: DataSource, passwordEncoder: PasswordEncoder): JdbcUserDetailsManager {
        return JdbcUserDetailsManager(dataSource).apply {
            if (userExists("admin")) deleteUser("admin")
            val hash = passwordEncoder.encode("password")
            User.withUsername("admin").password(hash).roles("admin").build().let(::createUser)
        }
    }

    @Bean
    fun seedData(service: TodoService) {
        val todo = TodoItem().apply {
            name = "Test app with seed data!"
        }
        service.save(todo)
    }
}
