package uk.co.davetheitguy.tiqqet.exceptions

abstract class ApplicationException(message: String?, cause: Throwable?) : RuntimeException(message, cause) {
    constructor(message: String?) : this(message, null)
}