package uk.co.davetheitguy.tiqqet.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {
    @GetMapping("/")
    fun homeEndpoint():String {
        return "home"
    }
}
