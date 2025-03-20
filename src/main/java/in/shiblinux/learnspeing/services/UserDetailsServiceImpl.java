package in.shiblinux.learnspeing.services;

import in.shiblinux.learnspeing.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserServices userServices;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= userServices.findByUserName(username);
        if (user !=null){

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassWord())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
        }

  throw  new UsernameNotFoundException("user not found"+ username);
    }
}


