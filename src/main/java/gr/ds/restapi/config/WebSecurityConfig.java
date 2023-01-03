package gr.ds.restapi.config;

import gr.ds.restapi.services.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    CustomUserDetails userDetails;


    private String testAuthQuery = "select u.username, r.name from user u join role r on u.role_id=r.id where username=?";
    private String oldQuery = "select u.username, r.name from user u join role r on u.user_id = r.user_id where username = ?";
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails);
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select u.username, u.passcode, u.enabled from user u where u.username = ?")
                .authoritiesByUsernameQuery(testAuthQuery);

    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.userDetailsService(userDetails);
        httpSecurity.cors();

        httpSecurity.httpBasic().and().authorizeRequests()
                .antMatchers("/api/root/home").hasRole("ADMIN")
                .antMatchers("/api/root/users/all").hasAuthority("VIEW_USERS")
                .antMatchers("/api/root/users/find").hasAuthority("VIEW_USERS")
                .antMatchers("/api/root/users/{username}").hasAuthority("VIEW_USER")
                .antMatchers("/api/root/users/{usertype}/all").hasAuthority("VIEW_USERS")
                .antMatchers("/api/root/users/delete/{username}").hasAuthority("DELETE_USER")
                .antMatchers("/api/root/users/add**").hasAuthority("ADD_USER")
                .antMatchers("/api/root/users/update**").hasAuthority("UPDATE_USER")
                .antMatchers("/citizen/home").hasRole("CITIZEN")
                .antMatchers("/citizen/add-pet").hasAnyAuthority("ADD_PET")
                .antMatchers("/citizen/pets").hasAnyAuthority("VIEW_PETS")
                .antMatchers("/citizen/pending-pets").hasAnyAuthority("VIEW_PETS")
                .antMatchers("/civic/home").hasRole("CIVIC")
                .antMatchers("/civic/show-pets").hasAnyAuthority("VIEW_PETS")
                .antMatchers("/vet/home").hasRole("VET")
                .antMatchers("/vet/show-pending").hasAnyAuthority("VIEW_PETS")
                .antMatchers("/vet/verify-pet").hasAnyAuthority("VERIFY_PET")
                .antMatchers("/vet/update-med-history").hasAnyAuthority("UPDATE_PET_HISTORY")
                .antMatchers("/vet/get-pet").hasAnyAuthority("VIEW_PETS")
                .and()
                .exceptionHandling().accessDeniedPage("/welcome/error")
                .and().csrf().disable().headers().frameOptions().disable()
                .and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/home")
                .and().logout().permitAll()/*.deleteCookies("JSESSIONID").invalidateHttpSession(true)*//*.clearAuthentication(true).deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)*/;

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
        web.ignoring().antMatchers("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(Collections.singletonList("http://localhost:3000"));
        config.setAllowedHeaders(Arrays.asList("Authorization","Origin", "Content-Type", "Accept", "X-Custom-Header", "X-Requested-With"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }



}
