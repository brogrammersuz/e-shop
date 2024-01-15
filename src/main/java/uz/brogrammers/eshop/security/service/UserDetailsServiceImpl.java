package uz.brogrammers.eshop.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.brogrammers.eshop.security.model.UserPrincipal;
import uz.brogrammers.eshop.user.service.UserService;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = userService.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("User or password incorrect"));

        return UserPrincipal.build(user);
    }
}
