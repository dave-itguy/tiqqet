package uk.co.davetheitguy.tiqqet.controllers

import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import uk.co.davetheitguy.tiqqet.data.TiqqetUser
import java.security.Principal

@RestController
class UserController(val userDetailsManager: UserDetailsManager, val passwordEncoder: PasswordEncoder) {
    @GetMapping("/user")
    fun getUser(principal: Principal): ResponseEntity<Principal> {
        return ResponseEntity.ok(principal)
    }

    @PostMapping("/user")
    fun createUser(@RequestBody user: TiqqetUser): ResponseEntity<Nothing?> {
        if (userDetailsManager.userExists(user.username)) return ResponseEntity.ok(null)
        User.withUsername(user.username).password(passwordEncoder.encode(user.password)).roles(*user.roles).build()
            .let(userDetailsManager::createUser)
        return ResponseEntity.ok(null)
    }
}
