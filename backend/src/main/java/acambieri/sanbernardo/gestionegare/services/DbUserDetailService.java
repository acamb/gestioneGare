package acambieri.sanbernardo.gestionegare.services;

import acambieri.sanbernardo.gestionegare.model.User;
import acambieri.sanbernardo.gestionegare.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Primary
@Qualifier("dbUserRepo")
public class DbUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameAndActiveIsTrue(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        if(user.getRoles() != null) {
            user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority( role.getName())));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.isActive(),
                true,
                true,
                true,
                authorities);
    }
}
