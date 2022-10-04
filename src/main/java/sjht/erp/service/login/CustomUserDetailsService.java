package sjht.erp.service.login;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sjht.erp.configure.UserNotFoundException;
import sjht.erp.model.dto.login.UserDto;
import sjht.erp.repository.login.UserMapper;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userMapper.findByUserId(Long.valueOf(userId))
                .map(user -> addAuthorities(user))
                .orElseThrow(() -> new UserNotFoundException(userId + ">찾을 수 없습니다."));
    }

    private UserDto addAuthorities(UserDto userDto){
        userDto.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userDto.getRole())));

        return userDto;
    }
}
