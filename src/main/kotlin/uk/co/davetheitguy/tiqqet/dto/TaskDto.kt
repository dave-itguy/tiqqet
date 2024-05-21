package uk.co.davetheitguy.tiqqet.dto

data class TaskDto (
    var name: String? = null,
    var description: String? = null,
    var status: String? = null,
    var user: String? = null
)