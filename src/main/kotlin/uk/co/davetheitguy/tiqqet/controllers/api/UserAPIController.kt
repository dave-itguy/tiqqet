package uk.co.davetheitguy.tiqqet.controllers.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import uk.co.davetheitguy.tiqqet.dto.UserObject
import uk.co.davetheitguy.tiqqet.exceptions.UserExistsException
import uk.co.davetheitguy.tiqqet.exceptions.ValidationException
import uk.co.davetheitguy.tiqqet.services.UserService

@RestController
class UserAPIController(val service: UserService) {
    @PostMapping("/api/v1/user")
    fun createUser(@RequestBody details: UserObject): ResponseEntity<Any?> {
        try {
            service.createUser(details)
        } catch (_: UserExistsException) {
            return ResponseEntity.noContent().build()
        } catch (e: ValidationException) {
            return ResponseEntity.badRequest().body(e)
        }
        return ResponseEntity.noContent().build()
    }
}

