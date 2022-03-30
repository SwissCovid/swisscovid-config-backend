package org.dpppt.switzerland.backend.sdk.config.ws.config;

import org.dpppt.switzerland.backend.sdk.config.ws.config.configbeans.ActuatorSecurityConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.boot.actuate.logging.LoggersEndpoint;
import org.springframework.boot.actuate.metrics.export.prometheus.PrometheusScrapeEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE + 9)
@Profile(value = "actuator-security")
@EnableWebSecurity
public class ActuatorSecurity extends WebSecurityConfigurerAdapter {

    private static final String PROMETHEUS_ROLE = "PROMETHEUS";

    @Value("${ws.monitor.prometheus.user}")
    private String user;
    @Value("${ws.monitor.prometheus.password}")
    private String password;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(
                        org.springframework.boot.actuate.autoconfigure.security.servlet
                                .EndpointRequest.toAnyEndpoint())
                .authorizeRequests()
                .requestMatchers(
                        org.springframework.boot.actuate.autoconfigure.security.servlet
                                .EndpointRequest.to(HealthEndpoint.class))
                .permitAll()
                .requestMatchers(
                        org.springframework.boot.actuate.autoconfigure.security.servlet
                                .EndpointRequest.to(InfoEndpoint.class))
                .permitAll()
                .requestMatchers(
                        org.springframework.boot.actuate.autoconfigure.security.servlet
                                .EndpointRequest.to(LoggersEndpoint.class))
                .hasRole(PROMETHEUS_ROLE)
                .requestMatchers(
                        org.springframework.boot.actuate.autoconfigure.security.servlet
                                .EndpointRequest.to(PrometheusScrapeEndpoint.class))
                .hasRole(PROMETHEUS_ROLE)
                .anyRequest()
                .denyAll()
                .and()
                .httpBasic();

        http.csrf().ignoringAntMatchers("/actuator/loggers/**");
    }

    @Override
    protected void configure(
            AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication()
                .withUser(user)
                .password(password)
                .roles(PROMETHEUS_ROLE);
    }


}
