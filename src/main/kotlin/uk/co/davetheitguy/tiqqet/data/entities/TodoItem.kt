package uk.co.davetheitguy.tiqqet.data.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0
    var name: String? = ""
}
