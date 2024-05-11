package uk.co.davetheitguy.tiqqet.exceptions

data class ValidationError(val propertyName: String, val error: String)