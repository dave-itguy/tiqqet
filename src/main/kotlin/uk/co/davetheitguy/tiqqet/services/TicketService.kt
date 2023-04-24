package uk.co.davetheitguy.tiqqet.services

import uk.co.davetheitguy.tiqqet.entities.Ticket
import java.util.*

interface TicketService {
    fun findAll(): List<Ticket>
    fun findById(id: Long): Optional<Ticket>
    fun save(ticket: Ticket): Ticket
    fun deleteById(id: Long)
    fun update(id: Long, ticket: Ticket): Ticket
}