package uk.co.davetheitguy.tiqqet.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.jetbrains.annotations.NotNull
import java.sql.Date

@Entity
class Ticket(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @NotNull
    var title: String = "",
    var description: String? = null,
    @NotNull
    var status: TicketStatus = TicketStatus.OPEN,
    var createdDate: Date = Date(System.currentTimeMillis()),
    var updatedDate: Date = Date(System.currentTimeMillis())
)

