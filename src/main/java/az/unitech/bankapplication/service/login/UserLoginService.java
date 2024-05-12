package az.unitech.bankapplication.service.login;

import az.unitech.bankapplication.dto.request.user.UserLoginRequest;
import az.unitech.bankapplication.dto.response.user.UserLoginResponse;
import az.unitech.bankapplication.entity.AccountEntity;
import az.unitech.bankapplication.entity.UserEntity;
import az.unitech.bankapplication.exception.UserNotFoundException;
import az.unitech.bankapplication.mapper.UserMapper;
import az.unitech.bankapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserLoginService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> loginUser(UserLoginRequest userLoginRequest) {
        UserEntity user = userRepository.findByUserName(userLoginRequest.getUserName());
        if (user == null) {
            throw new UserNotFoundException("Istifadeci tapilmadi");
        }

        if (!user.getPassword().equals(userLoginRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Xetali istifadeci adı ve ya şifre");
        }

        UserLoginResponse responseDTO = userMapper.entityToLoginResponse(user);
        return ResponseEntity.ok(responseDTO);
    }

    public boolean authenticate(String username, String password) {
        UserEntity user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UserNotFoundException("Istifadeci tapilmadi");
        }
        //Userin daxil etdiyi sifre ile databasede olan sifre eynidirse method true verir
        return user.getPassword().equals(password);
    }

//    public boolean authenticate(String username) {
//        UserEntity user = userRepository.findByUserName(username);
//        if (user == null) {
//            throw new UserNotFoundException("Istifadeci tapilmadi");
//        }
//        return true;
//    }
    //+authorization

    public Long getLoggedInUserAccountId(String username, String password) {
        if (authenticate(username, password)) {
            UserEntity user = userRepository.findByUserName(username);
            List<AccountEntity> accounts = user.getAccounts();
            if (!accounts.isEmpty()) {
                return accounts.get(0).getId();
            }
        }
        return null;
    }


}
