package uk.co.davetheitguy.tiqqet.security.jwt.filter

import io.jsonwebtoken.Claims
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import uk.co.davetheitguy.tiqqet.security.jwt.config.JwtConfig
import uk.co.davetheitguy.tiqqet.security.jwt.data.LocalPrincipal
import uk.co.davetheitguy.tiqqet.security.jwt.services.JwtUtils

class JwtTokenAuthenticationFilter(private val config: JwtConfig, private val utils: JwtUtils) : OncePerRequestFilter() {
    public override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val header = request.getHeader(config.header)

        if (header == null || !header.startsWith(config.prefix!!)) {
            filterChain.doFilter(request, response)
            return
        }

        val token = header.replace(config.prefix!!, "").trim()

        try {
            val claims: Claims = utils.parseToken(token)

            val username = claims.subject

            if (username != null) {

                val userId = claims.id.toLong()

                val authorities: List<GrantedAuthority>

                val authoritiesClaim = claims["authorities"] as String

                authorities = authoritiesClaim.split(',')
                    .map { a -> SimpleGrantedAuthority("ROLE_" + a.uppercase()) }
                    .toList()

                val rawGroupIds = claims["groupIds"] as String

                val groupIds: List<Long> = rawGroupIds.trim()
                    .split(',')
                    .filter { it.isNotBlank() }
                    .map { it.toLong() }
                    .toList()

                val principal = LocalPrincipal(userId, username, groupIds)

                val auth = UsernamePasswordAuthenticationToken(principal, null, authorities)

                SecurityContextHolder.getContext().authentication = auth
            }
        } catch (e: Exception) {
            SecurityContextHolder.clearContext()
        }

        filterChain.doFilter(request, response)
    }
}
