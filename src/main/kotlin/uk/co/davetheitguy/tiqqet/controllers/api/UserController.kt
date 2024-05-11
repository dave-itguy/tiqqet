package uk.co.davetheitguy.tiqqet.controllers.api

import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(val manager: UserDetailsManager) {
    @PostMapping("api/v1/user")
    fun createUser(@RequestBody details: UserDetails): ResponseEntity<Nothing> {
        if (manager.userExists(details.username)) return ResponseEntity.noContent().build()
        val user = User.withUsername(details.username)
            .password(details.password)
            .roles("USER")
        manager.createUser(user.build());
        return ResponseEntity.noContent().build()
    }
}