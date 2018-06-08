package com.mindhubweb.salvo.configuration;

import com.mindhubweb.salvo.model.Player;
import com.mindhubweb.salvo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inputName-> {
            Player player = playerRepository.findByEmail(inputName);
            if (player != null) {
                return new User(player.getEmail(), player.getPassword(),
                        AuthorityUtils.createAuthorityList("USER"));
            } else {
                throw new UsernameNotFoundException("Unknown user: " + inputName);
            }
        });
    }
}
