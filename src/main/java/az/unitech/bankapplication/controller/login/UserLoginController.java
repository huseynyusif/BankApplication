package az.unitech.bankapplication.controller.login;

import az.unitech.bankapplication.dto.request.user.UserCreateRequest;
import az.unitech.bankapplication.dto.request.user.UserLoginRequest;
import az.unitech.bankapplication.entity.UserEntity;
import az.unitech.bankapplication.exception.UserNotFoundException;
import az.unitech.bankapplication.service.create.UserCreateService;
import az.unitech.bankapplication.service.login.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserLoginController {
    private final UserLoginService userLoginService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        ResponseEntity<?> responseEntity;
        try {
            ResponseEntity<?> responseDTO = userLoginService.loginUser(userLoginRequest);
            responseEntity = ResponseEntity.ok(responseDTO);
        } catch (UserNotFoundException e) {
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Xəta baş verdi");
        }
        return responseEntity;
    }
}
