package uk.co.davetheitguy.tiqqet.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import org.jetbrains.annotations.NotNull

@Entity
class Task {
    @Id
    @GeneratedValue
    var id: Long= 0L
    @NotNull
    var name: String? = null
    var description: String? = null
    @ManyToOne
    var status: Status? = null
    @NotNull
    var user: String? = null
}

