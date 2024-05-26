package uk.co.davetheitguy.tiqqet.security.jwt.services.implementations

import io.jsonwebtoken.Jwts
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import uk.co.davetheitguy.tiqqet.security.jwt.config.JwtConfig
import java.util.*

class JwtUtilsImplTest {
    private lateinit var jwtConfig: JwtConfig
    private lateinit var jwtUtilsImpl: JwtUtilsImpl
    private val tokenValue = Base64.getEncoder()
        .encodeToString("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWX".toByteArray())


    @BeforeEach
    fun setUp() {
        jwtConfig = mock(JwtConfig::class.java)

        `when`(jwtConfig.secret).thenReturn(tokenValue)
        `when`(jwtConfig.issuer).thenReturn("your-issuer")
        `when`(jwtConfig.audience).thenReturn("your-audience")
        `when`(jwtConfig.expiration).thenReturn(3600L)

        jwtUtilsImpl = JwtUtilsImpl(jwtConfig)
    }

    @Test
    fun generateToken() {
        val id = "1234"
        val username = "testuser"
        val roles = listOf("ROLE_USER", "RLE_ADMIN")
        val groupIds = listOf(1L, 2L, 3L)

        val token = jwtUtilsImpl.generateToken(id, username, roles, groupIds)
        Assertions.assertNotNull(token)

        val claims = Jwts.parserBuilder()
            .requireAudience(jwtConfig.audience)
            .setSigningKey(Base64.getDecoder().decode(jwtConfig.secret))
            .build()
            .parseClaimsJws(token)
            .body

        Assertions.assertEquals(id, claims.id)
        Assertions.assertEquals(username, claims.subject)
        Assertions.assertEquals("ROLE_USER,RLE_ADMIN", claims["authorities"])
        Assertions.assertEquals("1,2,3,", claims["groupIds"])
        Assertions.assertEquals(jwtConfig.issuer, claims.issuer)
    }

    @Test
    fun checkTokenWithValidToken() {
        val token = jwtUtilsImpl.generateToken("1234", "testuser", listOf("ROLE_USER"), listOf(1L))
        Assertions.assertTrue(jwtUtilsImpl.checkToken(token))
    }

    @Test
    fun checkTokenWithInvalidToken() {
        Assertions.assertFalse(jwtUtilsImpl.checkToken("invalidToken"))
    }

    @Test
    fun testParseTokenWithValidToken() {
        val token = jwtUtilsImpl.generateToken("1234", "testuser", listOf("ROLE_USER"), listOf(1L))
        val claims = jwtUtilsImpl.parseToken(token)

        Assertions.assertEquals("1234", claims.id)
        Assertions.assertEquals("testuser", claims.subject)
    }

    @Test
    fun testParseTokenWithInvalidToken() {
        Assertions.assertThrows(Exception::class.java) {
            jwtUtilsImpl.parseToken("invalidToken")
        }
    }
}
