package uk.co.davetheitguy.tiqqet

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.UserDetailsManager
import uk.co.davetheitguy.tiqqet.dto.TaskDto
import uk.co.davetheitguy.tiqqet.security.jwt.annotations.EnableJwtTokenFiltering
import uk.co.davetheitguy.tiqqet.services.TaskService


@EnableJwtTokenFiltering
@SpringBootApplication
class TiqqetApplication

fun main(args: Array<String>) {
    runApplication<TiqqetApplication>(*args)
}

@Profile("debug")
@Configuration
class DebugConfiguration {
    @Bean
    fun seedBean(userDetailsManager: UserDetailsManager, taskService: TaskService) = CommandLineRunner {
        if(userDetailsManager.userExists("testuser")) return@CommandLineRunner
        val user = User.builder()
                .username("testuser")
                .password("{noop}password")
                .roles("USER")
                .build()
        userDetailsManager.createUser(user)
        val taskDto = TaskDto("Test Task", null, "New", "testuser")
        taskService.create(taskDto)
    }
}
