package uk.co.davetheitguy.tiqqet.exceptions

class ValidationException(val exceptions: Array<ValidationError>) : TiqqetException("Validation failed")
