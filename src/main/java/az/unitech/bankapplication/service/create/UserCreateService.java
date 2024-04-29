package az.unitech.bankapplication.service.create;

import az.unitech.bankapplication.dto.request.user.UserCreateRequest;
import az.unitech.bankapplication.dto.response.user.UserCreateResponse;
import az.unitech.bankapplication.entity.User;
import az.unitech.bankapplication.mapper.UserMapper;
import az.unitech.bankapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserCreateService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public ResponseEntity<?> createUser(UserCreateRequest userCreateRequestDTO) {

        User user = userMapper.entityToCreateRequest(userCreateRequestDTO);

        if (userRepository.existsByPin(user.getPin())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bu FIN kod ile artiq bir istifadeci movcuddur");
        }else if (userRepository.existsByEmail(user.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bu email ile artiq bir istifadeci movcuddur");
        }

        User createdUser = userRepository.save(user);

        UserCreateResponse responseDTO = userMapper.entityToCreateResponse(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
