package com.moviebookingapp.user.configuration;

import com.moviebookingapp.user.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true,proxyTargetClass = true)//takes care of role based authentication
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String[] PUBLIC_URLS={
            "/api/v1/moviebooking/login",
            "/login",
            "/api/v1/moviebooking/all",
            "/api/v1/moviebooking/movies/search/**",
            "/api/v1/moviebooking/forgotPassword",
            "/api/v1/moviebooking/omdb/**",
            "/api/v1/moviebooking/register",
            "/v3/api-docs",
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**",
            "MBapp-env.eba-yevxmkrm.us-east-1.elasticbeanstalk.com/all",
            "MBapp-env.eba-yevxmkrm.us-east-1.elasticbeanstalk.com/movies/search/**",
            "MBapp-env.eba-yevxmkrm.us-east-1.elasticbeanstalk.com/forgotPassword",
            "MBapp-env.eba-yevxmkrm.us-east-1.elasticbeanstalk.com/omdb/**",
            "MBapp-env.eba-yevxmkrm.us-east-1.elasticbeanstalk.com/register",
            "moviebooking.us-east-1.elasticbeanstalk.com/login",
            "**/all"

    };

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private UserDetailsService jwtService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable()
                .authorizeRequests().antMatchers(PUBLIC_URLS).permitAll()
                .antMatchers(HttpHeaders.ALLOW).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        ;

//        http.logout((logout)-> logout
//                .logoutUrl("/my/logout")
//                .logoutSuccessUrl("/api/v1/moviebooking/forUser")
//               .permitAll()
//                .invalidateHttpSession(true)
//        );
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(jwtService).passwordEncoder(passwordEncoder());
    }


}
