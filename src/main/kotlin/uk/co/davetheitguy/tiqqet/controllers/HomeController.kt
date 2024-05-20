package uk.co.davetheitguy.tiqqet.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import uk.co.davetheitguy.tiqqet.services.TaskService

@Controller
class HomeController(val taskService: TaskService) {
    @GetMapping("/")
    fun homeEndpoint(model:Model):String {
        model.addAttribute("tasks", taskService.getAll())
        return "home"
    }
}
