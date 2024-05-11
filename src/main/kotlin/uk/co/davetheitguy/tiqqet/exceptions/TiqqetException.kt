package uk.co.davetheitguy.tiqqet.exceptions

abstract class TiqqetException(message: String?, cause: Throwable?) : ApplicationException(message, cause) {
    constructor(message: String?) : this(message, null)
}