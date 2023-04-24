package uk.co.davetheitguy.tiqqet.repositories

import org.springframework.data.jpa.repository.JpaRepository
import uk.co.davetheitguy.tiqqet.entities.Ticket

interface TicketRepository : JpaRepository<Ticket, Long>