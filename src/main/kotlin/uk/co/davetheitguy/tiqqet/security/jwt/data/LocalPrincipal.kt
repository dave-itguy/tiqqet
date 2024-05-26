package uk.co.davetheitguy.tiqqet.security.jwt.data

data class LocalPrincipal(val userId: Long = 0L, val username: String? = null, val groupIds: List<Long> = listOf())
