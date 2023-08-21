package uk.co.davetheitguy.tiqqet.data

data class TiqqetUser(var username: String, var password: String, var roles: Array<String>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TiqqetUser

        if (username != other.username) return false
        if (password != other.password) return false
        if (!roles.contentEquals(other.roles)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = username.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + roles.contentHashCode()
        return result
    }
}
