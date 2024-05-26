package uk.co.davetheitguy.tiqqet.security.jwt.services.implementations

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import uk.co.davetheitguy.tiqqet.security.jwt.config.JwtConfig
import uk.co.davetheitguy.tiqqet.security.jwt.services.JwtUtils
import java.util.*
import java.util.function.Consumer

internal class JwtUtilsImpl(val config: JwtConfig) : JwtUtils {
    override fun generateToken(id: String, username: String, roles: List<String>, groupIds: List<Long>): String {
        val claims = Jwts.claims().setId(id).setSubject(username)
        claims["authorities"] = java.lang.String.join(",", roles)
        val builder = StringBuilder()
        groupIds.forEach(Consumer { i: Long? -> builder.append(i).append(",") })
        claims["groupIds"] = builder.toString()
        val key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(config.secret))
        return Jwts.builder()
            .setClaims(claims)
            .setIssuer(config.issuer)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + config.expiration * 1000))
            .signWith(key)
            .compact()
    }

    override fun checkToken(token: String): Boolean {
        try {
            parseToken(token)
        } catch (e: Exception) {
            return false
        }
        return true
    }

    override fun parseToken(token: String): Claims {
        return Jwts.parserBuilder()
            .requireAudience(config.audience)
            .build()
            .parseClaimsJws(token)
            .body
    }
}
