package sandwich.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import sandwich.config.jwt.JwtAuthFilter;
import sandwich.config.jwt.JwtTokenProvider;

@Configuration
@EnableWebSecurity()
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private JwtTokenProvider provider;

    public SecurityConfig(JwtTokenProvider provider)
    {
        this.provider = provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.cors().and().csrf().disable()
                .authorizeRequests()
//            .antMatchers("/h2-console/**").permitAll()
//            .antMatchers("/swagger-ui/**").permitAll() // http://localhost:8080/swagger-ui/index.html
//            .antMatchers("/v2/api-docs").permitAll() // http://localhost:8080/v2/api-docs
//            .antMatchers("/webjars/**").permitAll()
//            .antMatchers("/swagger-ressources").permitAll()
//            .antMatchers("/swagger-ressources/configuration/ui").permitAll()
//            .antMatchers("/swagger-ressources/configuration/security").permitAll()
//            .antMatchers("/ws/**").permitAll()
//
//            .antMatchers(HttpMethod.POST,
//            "/login",
//            "/register"
//                ).permitAll()
//            .antMatchers(HttpMethod.GET, "/address/**").hasAnyAuthority("ADMIN")
//            .antMatchers(HttpMethod.POST,
//                "/user/**",
//                "/address/**",
//                "/category/**",
//                "/product/**"
//            ).hasAnyAuthority("ADMIN")
//            .antMatchers(HttpMethod.PUT,
//                "/category/**",
//                "/product/**"
//            ).hasAnyAuthority("ADMIN")
//            .antMatchers(HttpMethod.DELETE,
//                "/address/**",
//                "/category/**",
//                "/product/**"
//            ).hasAnyAuthority("ADMIN")
//            .anyRequest().authenticated()
                .anyRequest().permitAll()
                .and().httpBasic();


        http.addFilterBefore(new JwtAuthFilter(this.provider), UsernamePasswordAuthenticationFilter.class);
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.headers()
                .frameOptions()
                .disable();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOrigin("http://localhost:4200/");
        config.addAllowedMethod("*"); // POST/GET/PUT/DELETE/OPTIONS/...
        config.addAllowedHeader("*");
        config.addExposedHeader("Authorization");
        config.setAllowCredentials(true);

        return request -> config;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception
    {
        return super.authenticationManager();
    }
}
