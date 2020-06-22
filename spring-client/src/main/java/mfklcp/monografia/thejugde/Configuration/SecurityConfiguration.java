/*
 * @Autor: Márcio Franklin
 * @E-mail: mfklcp@gmail.com
 * @License: MIT
 *
 */

package mfklcp.monografia.thejugde.Configuration;

import mfklcp.monografia.thejugde.Configuration.Handler.RedirecionaLoginParaAreaHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RedirecionaLoginParaAreaHandler redirecionaLoginParaAreaHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/registration").permitAll()
                    .antMatchers("/admin").hasAuthority("ADMIN")
                    .antMatchers("/aluno").hasAuthority("ALUNO")
                    .antMatchers("/professor").hasAuthority("PROFESSOR")
                    .antMatchers("/questao").permitAll()
                .anyRequest()
                    .authenticated().and().csrf().disable().formLogin()
                .loginPage("/login")
                    .successHandler(redirecionaLoginParaAreaHandler)
                    .failureUrl("/login?error=true")
                    .usernameParameter("email")
                    .passwordParameter("senha")
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
        http.headers().frameOptions().disable();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

}
