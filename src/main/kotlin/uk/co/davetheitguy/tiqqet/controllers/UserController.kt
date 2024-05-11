package uk.co.davetheitguy.tiqqet.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import uk.co.davetheitguy.tiqqet.dto.UserObject
import uk.co.davetheitguy.tiqqet.exceptions.ValidationException
import uk.co.davetheitguy.tiqqet.services.UserService

@Controller
class UserController(val service: UserService) {
    @GetMapping("/user")
    fun userEndpoint(model: Model): String {
        model.addAttribute("user", UserObject())
        return "user"
    }

    @PostMapping("/user")
    fun createUser(@ModelAttribute user: UserObject, model: Model): String {
        try {
            user.validate()
        } catch (e: ValidationException) {
            model.addAttribute("exception", e)
            model.addAttribute("user", user)
            return "user"
        }
        service.createUser(user)
        return "redirect:/"
    }
}