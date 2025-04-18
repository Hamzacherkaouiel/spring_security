package security.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import security.spring.Models.Users;
import security.spring.Repository.Userepo;

@Service
public class ServiceUser {
    @Autowired
    Userepo userepo;
    @Autowired
    AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder bCrypt=new BCryptPasswordEncoder(12);

    public Users save(Users users){
        users.setPassword(bCrypt.encode(users.getPassword()));
        return this.userepo.save(users);

    }

    public String verify(Users user) {
        Authentication authentication=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()){
            return "Succes";
        }
        return "fail";
    }
}
