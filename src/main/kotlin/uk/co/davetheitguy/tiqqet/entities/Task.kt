package uk.co.davetheitguy.tiqqet.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import org.jetbrains.annotations.NotNull

@Entity
class Task(
    @Id
    @GeneratedValue
    var id: Long = 0L,
    @NotNull
    var name: String? = null,
    var description: String? = null,
    @ManyToOne
    var status: Status? = null,
    @NotNull
    var username: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Task

        if (name != other.name) return false
        if (description != other.description) return false
        if (status != other.status) return false
        if (username != other.username) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (status?.hashCode() ?: 0)
        result = 31 * result + (username?.hashCode() ?: 0)
        return result
    }
}

