package az.unitech.bankapplication.service.create;

import az.unitech.bankapplication.dto.request.user.UserCreateRequest;
import az.unitech.bankapplication.dto.response.user.UserCreateResponse;
import az.unitech.bankapplication.entity.UserEntity;
import az.unitech.bankapplication.mapper.UserMapper;
import az.unitech.bankapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserCreateService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> createUser(UserCreateRequest userCreateRequestDTO) {

        UserEntity user = userMapper.entityToCreateRequest(userCreateRequestDTO);

        if (userRepository.existsByPin(user.getPin())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bu FIN kod ile artiq bir istifadeci movcuddur");
        } else if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bu email ile artiq bir istifadeci movcuddur");
        } else if (userRepository.existsByUserName(user.getUserName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bu username ile artiq bir istifadeci movcuddur");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        UserEntity createdUser = userRepository.save(user);

        UserCreateResponse responseDTO = userMapper.entityToCreateResponse(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
