package sjht.erp.repository.login;

import org.apache.ibatis.annotations.Mapper;
import sjht.erp.model.dto.login.UserDto;

import java.util.Optional;

@Mapper
public interface UserMapper{
        Optional<UserDto> findUserByUsername(String username);
        Optional<UserDto> findByUserId(Long userId);
        void save(UserDto userDto);
}
