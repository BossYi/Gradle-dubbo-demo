package priv.yimeng.demo.configuration.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import priv.yimeng.demo.persistence.entity.AuthorityDO;
import priv.yimeng.demo.persistence.entity.UserDO;
import priv.yimeng.demo.service.UserService;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Desc
 *
 * @author yimeng
 * @date 2017-12-09
 */
@Component
public class CustomUserDetailServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CustomUserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDO userDO = userService.findByUsernameCaseInsenstive(username.toLowerCase());
        if (userDO == null) {
            throw new UsernameNotFoundException("User" + username + "not found!");
        }

        Collection<GrantedAuthority> authorityDOList = new ArrayList<>();

        for (AuthorityDO authorityDO : userDO.getAuthorities()) {
            authorityDOList.add(new SimpleGrantedAuthority(authorityDO.getName()));
        }

        return new User(userDO.getUsername(), userDO.getPassword(), authorityDOList);
    }
}
