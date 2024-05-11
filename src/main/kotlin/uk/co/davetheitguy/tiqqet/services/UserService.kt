package uk.co.davetheitguy.tiqqet.services

import uk.co.davetheitguy.tiqqet.dto.UserObject

interface UserService {
    fun createUser(user: UserObject)
}
