package az.unitech.bankapplication.controller.delete;

import az.unitech.bankapplication.service.delete.AccountDeletionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountDeletionController {

    private final AccountDeletionService accountDeletionService;

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccount(@RequestParam String username, @RequestParam String password,@RequestParam String accountNumber) {
        return accountDeletionService.deleteAccount(username, password,accountNumber);
    }

}
