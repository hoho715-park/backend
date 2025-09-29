import com.metaverse.backend.domain.User;
import com.metaverse.backend.dto.UserLoginDto;
import com.metaverse.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 로그인 처리
    public User loginUser(UserLoginDto dto) {
        Optional<User> userOpt = userRepository.findByUserId(dto.getUserId());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
                return user; // 로그인 성공
            }
        }

        return null; // 로그인 실패
    }
}
