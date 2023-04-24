package uk.co.davetheitguy.tiqqet.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import uk.co.davetheitguy.tiqqet.entities.Ticket
import uk.co.davetheitguy.tiqqet.services.TicketService

@RestController
class TicketController(val ticketService: TicketService) {
    @GetMapping("/tickets")
    fun findAll() = ResponseEntity.ok(ticketService.findAll())

    @GetMapping("/tickets/{id:\\d+}")
    fun findById(@PathVariable("id") id: Long) = ResponseEntity.of(ticketService.findById(id))

    @PostMapping("/tickets")
    fun save(@RequestBody ticket: Ticket) = ResponseEntity.ok(ticketService.save(ticket))

    @PutMapping("/tickets/{id:\\d+}")
    fun update(@PathVariable("id") id: Long, @RequestBody ticket: Ticket) =
        ResponseEntity.ok(ticketService.update(id, ticket))

    @DeleteMapping("/tickets/{id:\\d+}")
    fun deleteById(@PathVariable("id") id: Long): ResponseEntity<*> {
        ticketService.deleteById(id)
        return ResponseEntity.ok().build<Nothing>()
    }
}