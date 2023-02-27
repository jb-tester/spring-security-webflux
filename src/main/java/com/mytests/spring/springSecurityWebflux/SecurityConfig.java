package com.mytests.spring.springSecurityWebflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorityReactiveAuthorizationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import static org.springframework.security.web.util.matcher.RegexRequestMatcher.regexMatcher;


/**
 * *
 * <p>Created by irina on 2/6/2023.</p>
 * <p>Project: spring-security-webflux</p>
 * *
 */
public class SecurityConfig {



    @Configuration
    @EnableWebFluxSecurity
    static class MultiSecurityHttpConfig {
        @Bean
        public MapReactiveUserDetailsService userDetailsService() {
            UserDetails user = User.withDefaultPasswordEncoder()
                    .username("user")
                    .password("jolt")
                    .roles("USER")
                    .build();
            UserDetails admin = User.withDefaultPasswordEncoder()
                    .username("admin")
                    .password("jolt")
                    .roles("ADMIN")
                    .build();
            return new MapReactiveUserDetailsService(user,admin);
        }
        /*@Order(Ordered.HIGHEST_PRECEDENCE)
        @Bean
        SecurityWebFilterChain adminHttpSecurity(ServerHttpSecurity http) {
            http
                    .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/admin/**")).authorizeExchange()
                    .anyExchange().hasRole("ADMIN")
                    .and()

                    .httpBasic(withDefaults())
                    .formLogin(withDefaults());
             return http.build();
        }
        @Bean
        public SecurityWebFilterChain userHttpSecurity(ServerHttpSecurity http) {
            http
                    .authorizeExchange(exchanges -> exchanges
                            .pathMatchers("/user/**")
                            .access(AuthorityReactiveAuthorizationManager.hasRole("USER")
                    ).and()

                    .httpBasic(withDefaults())
                    .formLogin(withDefaults()));
            return http.build();
        }*/
        @Bean
        public SecurityWebFilterChain secretAndBarSecurityFilterChain(ServerHttpSecurity http) {
            http.authorizeExchange()
                    .pathMatchers("/secret/**")
                    .hasRole("ADMIN")
                    .pathMatchers("/bar/**")
                    .hasAuthority("ROLE_USER")
                    .pathMatchers("/principal1","/principal2").authenticated()
                    .pathMatchers("/case_zero/**").hasRole("ADMIN")
                    .pathMatchers("/case_third/**").hasRole("ADMIN")
                    .pathMatchers("/case_first/**","/case_second/**").authenticated()
                    .pathMatchers("/**").permitAll()
                    .and().httpBasic();
            return http.build();
        }

        @Bean
        MyPrincipalBean myPrincipalBean() {
            return new MyPrincipalBean();
        }

        static class MyPrincipalBean {

            public String username(UserDetails user) {
                return user.getUsername();
            }

        }
    }


}







