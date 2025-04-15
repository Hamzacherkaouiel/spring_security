package security.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.spring.Models.Users;
import security.spring.Repository.Userepo;
@Service
public class MyUserService implements UserDetailsService {
    @Autowired
    Userepo userepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user= this.userepo.findByUsername(username).get();
        if(user ==null){
            System.out.println("user not found");
            throw new UsernameNotFoundException("USER NOT FOUND");
        }

        return new UserPrincipal(user);
    }
}
