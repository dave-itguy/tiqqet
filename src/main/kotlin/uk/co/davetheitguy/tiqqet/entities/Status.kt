package uk.co.davetheitguy.tiqqet.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.jetbrains.annotations.NotNull

@Entity
class Status {
    @Id
    @GeneratedValue
    var id: Long= 0L
    @NotNull
    var name: String? = null
}