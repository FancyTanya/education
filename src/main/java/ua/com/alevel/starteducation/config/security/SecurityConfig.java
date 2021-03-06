package ua.com.alevel.starteducation.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ua.com.alevel.starteducation.Routes;
import ua.com.alevel.starteducation.config.security.filters.JWTAuthenticationFilter;
import ua.com.alevel.starteducation.config.security.filters.JWTAuthorizationFilter;
import ua.com.alevel.starteducation.config.security.properties.EducationSecurityProperties;
import ua.com.alevel.starteducation.service.UserService;


@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(EducationSecurityProperties.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final EducationSecurityProperties securityProperties;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    public SecurityConfig(EducationSecurityProperties securityProperties,
                          UserService userService, PasswordEncoder passwordEncoder,
                          ObjectMapper objectMapper) {
        this.securityProperties = securityProperties;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // open static resources
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                // open swagger-ui
                .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                // allow user registration and refresh, ignore authorization filters on login
                .antMatchers(HttpMethod.POST, Routes.USERS, Routes.TOKEN + "/refresh").permitAll()
                .antMatchers(HttpMethod.POST, Routes.TEACHERS, Routes.TOKEN +"/refresh").permitAll()
                .antMatchers(HttpMethod.POST, Routes.STUDENTS, Routes.TOKEN +"/refresh").permitAll()
                // admin can register new admins
                .antMatchers(HttpMethod.POST, Routes.USERS + "/admins").hasRole("ADMIN")
                // regular users can view basic user info for other users
                .antMatchers(HttpMethod.GET,Routes.USERS + "/{id:\\d+}").authenticated()
                // admin can manage users by id
                .antMatchers(Routes.USERS + "/{id:\\d+}/**").hasRole("ADMIN")
                // admin can use Actuator endpoints
                .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ADMIN")
                // by default, require authentication
                .anyRequest().authenticated()
                .and()
                // auth filter
                .addFilter(jwtAuthenticationFilter())
                // jwt-verification filter
                .addFilter(jwtAuthorizationFilter())
                // for unauthorized requests return 401
                .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                // allow cross-origin requests for all endpoints
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .csrf().disable()
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    private JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        var filter = new JWTAuthenticationFilter(authenticationManager(), objectMapper);
        filter.setFilterProcessesUrl(Routes.TOKEN);
        return filter;
    }

    private JWTAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        return new JWTAuthorizationFilter(authenticationManager(), securityProperties.getJwt());
    }

    private CorsConfigurationSource corsConfigurationSource() {
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
