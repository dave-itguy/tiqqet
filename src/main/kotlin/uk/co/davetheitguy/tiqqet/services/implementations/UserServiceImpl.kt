package uk.co.davetheitguy.tiqqet.services.implementations

import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.stereotype.Service
import uk.co.davetheitguy.tiqqet.dto.UserObject
import uk.co.davetheitguy.tiqqet.exceptions.UserExistsException
import uk.co.davetheitguy.tiqqet.exceptions.ValidationException
import uk.co.davetheitguy.tiqqet.services.UserService

@Service
class UserServiceImpl(val manager: UserDetailsManager) : UserService {
    @Throws(UserExistsException::class, ValidationException::class)
    override fun createUser(user: UserObject) {
        if (manager.userExists(user.username)) throw UserExistsException()
        user.validate()
        val u = User.withUsername(user.username)
            .password(user.password)
            .roles("USER")
            .build()
        manager.createUser(u)
    }
}