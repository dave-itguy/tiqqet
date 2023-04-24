package uk.co.davetheitguy.tiqqet.services.implementations

import org.springframework.stereotype.Service
import uk.co.davetheitguy.tiqqet.entities.Ticket
import uk.co.davetheitguy.tiqqet.repositories.TicketRepository
import uk.co.davetheitguy.tiqqet.services.TicketService
import java.util.*

@Service
class TicketServiceImpl(val ticketRepository: TicketRepository) : TicketService {
    override fun findAll(): List<Ticket> = ticketRepository.findAll()
    override fun findById(id: Long): Optional<Ticket> = ticketRepository.findById(id)
    override fun save(ticket: Ticket): Ticket = ticketRepository.saveAndFlush(ticket)
    override fun deleteById(id: Long) = ticketRepository.deleteById(id)
    override fun update(id: Long, ticket: Ticket): Ticket {
        ticketRepository.saveAndFlush(ticket);
        return ticket
    }
}