package az.unitech.bankapplication.controller.create;

import az.unitech.bankapplication.dto.request.account.AccountCreateRequest;
import az.unitech.bankapplication.dto.request.transaction.TransactionCreateRequest;
import az.unitech.bankapplication.dto.response.account.AccountReadResponse;
import az.unitech.bankapplication.entity.AccountEntity;
import az.unitech.bankapplication.service.create.AccountCreateService;
import az.unitech.bankapplication.service.login.UserLoginService;
import az.unitech.bankapplication.service.read.AccountReadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountCreateController {

    private final AccountCreateService accountCreateService;
    private final UserLoginService userLoginService;
    private final AccountReadService accountReadService;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody @Valid AccountCreateRequest accountCreateRequestDTO) {
        ResponseEntity<?> response;
        try {
            var responseDTO = accountCreateService.createAccount(accountCreateRequestDTO);
            response = ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (RuntimeException e) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return response;
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferMoney(@RequestParam String username, @RequestParam String password, @RequestBody TransactionCreateRequest transactionCreateRequest) {
        try {
            if (userLoginService.authenticate(username, password)) {
                // Pul kocurmeni sadece oz hesabindan ede biler
                Long accountId = userLoginService.getLoggedInUserAccountId(username, password);
                if (transactionCreateRequest.getSenderAccountId() == accountId) {
                    accountCreateService.transfer(
                            transactionCreateRequest.getSenderAccountId(),
                            transactionCreateRequest.getReceiverAccountId(),
                            transactionCreateRequest.getAmount()
                    );
                    return ResponseEntity.status(HttpStatus.CREATED).build();
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sadece oz hesabinizdan transfer ede bilersiz");
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Istifadeci adi ve ya sifre yanlisdir");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

//    @PostMapping("/transfer")
//    public ResponseEntity<?> transferMoney(Authentication authentication, @RequestBody TransactionCreateRequest transactionCreateRequest) {
//        try {
//            String username = authentication.getName();
//
//            if (userLoginService.authenticate(username)) {
//                // Pul kocurmeni sadece oz hesabindan ede biler
//                List<AccountReadResponse> accounts = accountReadService.getAccountsByUsername(username);
//                if (!(accounts == null)) {
//                    accountCreateService.transfer(
//                            transactionCreateRequest.getSenderAccountId(),
//                            transactionCreateRequest.getReceiverAccountId(),
//                            transactionCreateRequest.getAmount()
//                    );
//                    return ResponseEntity.status(HttpStatus.CREATED).build();
//                } else {
//                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sadece oz hesabinizdan transfer ede bilersiz");
//                }
//            } else {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Istifadeci adi ve ya sifre yanlisdir");
//            }
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }

}
