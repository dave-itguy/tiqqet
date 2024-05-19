package uk.co.davetheitguy.tiqqet.services

interface Mapper<in TFrom, out TTo> {
    fun map(from: TFrom): TTo
}