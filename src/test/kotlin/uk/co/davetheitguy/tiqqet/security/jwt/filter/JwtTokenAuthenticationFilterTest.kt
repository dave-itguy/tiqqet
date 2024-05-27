package uk.co.davetheitguy.tiqqet.security.jwt.filter

import io.jsonwebtoken.Claims
import jakarta.servlet.FilterChain
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import uk.co.davetheitguy.tiqqet.security.jwt.config.JwtConfig
import uk.co.davetheitguy.tiqqet.security.jwt.data.LocalPrincipal
import uk.co.davetheitguy.tiqqet.security.jwt.services.JwtUtils
import java.util.*

class JwtTokenAuthenticationFilterTest {

    private lateinit var jwtConfig: JwtConfig
    private lateinit var jwtUtils: JwtUtils
    private lateinit var filter: JwtTokenAuthenticationFilter

    private lateinit var request: MockHttpServletRequest
    private lateinit var response: MockHttpServletResponse
    private lateinit var filterChain: FilterChain

    private val tokenValue = Base64.getEncoder()
        .encodeToString("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWX".toByteArray())

    @BeforeEach
    fun setUp() {
        jwtConfig = mock(JwtConfig::class.java)
        jwtUtils = mock(JwtUtils::class.java)
        filter = JwtTokenAuthenticationFilter(jwtConfig, jwtUtils)

        request = MockHttpServletRequest()
        response = MockHttpServletResponse()
        filterChain = mock(FilterChain::class.java)

        `when`(jwtConfig.header).thenReturn("Authorization")
        `when`(jwtConfig.prefix).thenReturn("Bearer ")
        `when`(jwtConfig.secret).thenReturn(tokenValue)
    }

    @Test
    fun testDoInternalWithValidToken() {
        val claims = mock(Claims::class.java)
        `when`(claims.subject).thenReturn("testuser")
        `when`(claims.id).thenReturn("1234")
        `when`(claims["authorities"]).thenReturn("USER,ADMIN")
        `when`(claims["groupIds"]).thenReturn("1,2,3")

        val token = "validToken"
        request.addHeader("Authorization", "Bearer $token")
        `when`(jwtUtils.parseToken(token)).thenReturn(claims)

        filter.doFilterInternal(request, response, filterChain)

        val auth = SecurityContextHolder.getContext().authentication as UsernamePasswordAuthenticationToken

        Assertions.assertNotNull(auth)
        Assertions.assertEquals("testuser", (auth.principal as LocalPrincipal).username)
        Assertions.assertEquals(1234, (auth.principal as LocalPrincipal).userId)
        Assertions.assertTrue(auth.authorities.contains(SimpleGrantedAuthority("ROLE_USER")))
        Assertions.assertTrue(auth.authorities.contains(SimpleGrantedAuthority("ROLE_ADMIN")))

        verify(filterChain).doFilter(request, response)
    }

    @Test
    fun testDoInternalWithInvalidToken() {
        request.addHeader("Authorization", "Bearer invalidToken")
        `when`(jwtUtils.parseToken("invalidToken")).thenThrow(RuntimeException::class.java)

        filter.doFilterInternal(request, response, filterChain)

        Assertions.assertNull(SecurityContextHolder.getContext().authentication)
        verify(filterChain).doFilter(request, response)
    }

    @Test
    fun testDoInternalWithoutToken() {
        filter.doFilterInternal(request, response, filterChain)

        Assertions.assertNull(SecurityContextHolder.getContext().authentication)
        verify(filterChain).doFilter(request, response)
    }

    @Test
    fun testDoInternalWithInvalidPrefix() {
        request.addHeader("Authorization", "invalidPrefixToken");

        filter.doFilterInternal(request, response, filterChain)

        Assertions.assertNull(SecurityContextHolder.getContext().authentication)
        verify(filterChain).doFilter(request, response)
    }
}
