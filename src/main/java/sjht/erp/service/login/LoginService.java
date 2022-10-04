package sjht.erp.service.login;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sjht.erp.configure.DuplicatedUsernameException;
import sjht.erp.configure.JwtTokenProvider;
import sjht.erp.configure.LoginFailedException;
import sjht.erp.configure.UserNotFoundException;
import sjht.erp.model.dto.login.LoginDto;
import sjht.erp.model.dto.login.UserDto;
import sjht.erp.repository.login.UserMapper;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class LoginService {

    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto join(UserDto userDto){
        if(userMapper.findUserByUsername(userDto.getUsername()).isPresent()){
            throw new DuplicatedUsernameException("이미 가입된 유저입니다.");
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userMapper.save(userDto);

        return userMapper.findUserByUsername(userDto.getUsername()).get();
    }

    public String login(LoginDto loginDto){
        UserDto userDto = userMapper.findUserByUsername(loginDto.getUsername())
                .orElseThrow(() -> new LoginFailedException("잘못된 아이디입니다."));

        if(!passwordEncoder.matches(loginDto.getPassword(), userDto.getPassword())){
            throw new LoginFailedException("잘못된 비밀번호 입니다.");
        }

        return jwtTokenProvider.createToken(userDto.getUserId(), Collections.singletonList(userDto.getRole()));

    }

    public UserDto findByUserId(Long userId){
        return userMapper.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("없는 유저입니다."));
    }
}
