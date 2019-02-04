package pl.edu.pjwstk.photoapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import pl.edu.pjwstk.photoapp.service.UserDetailsServiceImpl;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {


        auth.userDetailsService(createUserDetailsService()).passwordEncoder(bCryptPasswordEncoder());

        auth.inMemoryAuthentication()
                .withUser("customer").password("password").roles("CUSTOMER")
                .and()
                .withUser("employee").password("password").roles("EMPLOYEE")
                .and()
                .withUser("admin").password("password").roles("ADMIN");

    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new UrlAuthenticationSuccessHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/calendar", "/contact", "/register", "/success").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/customer/**").hasAnyRole("CUSTOMER")
                .antMatchers("/employee/**").hasAnyRole("EMPLOYEE")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(myAuthenticationSuccessHandler())
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/static/css/js/**", "/images/**");
    }

    @Bean
    public UserDetailsService createUserDetailsService() {
        return new UserDetailsServiceImpl();
    }


}
