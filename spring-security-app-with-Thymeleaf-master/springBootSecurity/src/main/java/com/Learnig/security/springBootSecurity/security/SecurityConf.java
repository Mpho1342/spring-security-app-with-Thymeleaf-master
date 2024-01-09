package com.Learnig.security.springBootSecurity.security;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConf {

    private CustomeUserDetailServices customeUserDetailServices;

    @Autowired
    public SecurityConf(CustomeUserDetailServices customeUserDetailServices) {
        this.customeUserDetailServices = customeUserDetailServices;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, MvcRequestMatcher.Builder mvc)
            throws Exception {

        httpSecurity
                .csrf(csr -> csr.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                // .securityMatcher("/register", "/login")
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(mvc.pattern("/register")).permitAll()
                        .requestMatchers(mvc.pattern("/login")).permitAll()
                        .requestMatchers(mvc.pattern("/logout")).permitAll()
                        .requestMatchers(mvc.pattern("/LOGOUT_success")).permitAll()
                        .requestMatchers(mvc.pattern("/home")).authenticated()

                        .anyRequest().authenticated())
                .formLogin(log -> log.loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                        .defaultSuccessUrl("/home")
                         .failureUrl("/login?error=true")
                        .permitAll())
                .rememberMe(re -> re.tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21)).key("lovecolourspin"))
                .logout(logoutt -> logoutt.logoutUrl("/logout")
                        .clearAuthentication(true)
                        .logoutSuccessUrl("/LOGOUT_success")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID").permitAll()

                );

        return httpSecurity.build();
    }

}
