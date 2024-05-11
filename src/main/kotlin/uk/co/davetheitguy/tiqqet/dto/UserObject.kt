package uk.co.davetheitguy.tiqqet.dto

import uk.co.davetheitguy.tiqqet.exceptions.ValidationError
import uk.co.davetheitguy.tiqqet.exceptions.ValidationException
import uk.co.davetheitguy.tiqqet.interfaces.Validatable

data class UserObject(var username: String? = null, var password: String? = null, var confirm: String? = null) :
    Validatable {
    override fun validate() {
        val exceptions = mutableListOf<ValidationError>()
        if (username.isNullOrBlank()) exceptions.add(ValidationError("username", "required"))
        if (password.isNullOrBlank()) exceptions.add(ValidationError("password", "required"))
        if (confirm.isNullOrBlank()) exceptions.add(ValidationError("confirm", "required"))
        if (confirm != password) exceptions.add(ValidationError("password|confirm", "nomatch"))
        if (exceptions.any()) throw ValidationException(exceptions.toTypedArray())
    }
}