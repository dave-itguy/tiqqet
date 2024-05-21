package uk.co.davetheitguy.tiqqet.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.jetbrains.annotations.NotNull

@Entity
class Status (
    @Id
    @GeneratedValue
    var id: Long= 0L,
    @NotNull
    var name: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Status

        return name == other.name
    }

    override fun hashCode(): Int {
        return name?.hashCode() ?: 0
    }
}