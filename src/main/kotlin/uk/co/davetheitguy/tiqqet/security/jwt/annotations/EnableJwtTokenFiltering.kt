package uk.co.davetheitguy.tiqqet.security.jwt.annotations

import org.springframework.context.annotation.Import
import uk.co.davetheitguy.tiqqet.security.jwt.config.JwtTokenAuthorizationConfig

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Import(JwtTokenAuthorizationConfig::class)
annotation class EnableJwtTokenFiltering
