package az.unitech.bankapplication.controller.create;

import az.unitech.bankapplication.dto.request.user.UserCreateRequest;
import az.unitech.bankapplication.exception.UserAlreadyExistsException;
import az.unitech.bankapplication.service.create.UserCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserCreateController {

    private final UserCreateService userService;


    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserCreateRequest userCreateRequestDTO) {
        ResponseEntity<?> responseEntity;
        try {

            ResponseEntity<?> responseDTO = userService.createUser(userCreateRequestDTO);
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (UserAlreadyExistsException e) {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return responseEntity;
    }

}
