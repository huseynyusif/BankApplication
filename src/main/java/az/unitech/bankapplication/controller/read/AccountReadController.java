package az.unitech.bankapplication.controller.read;

import az.unitech.bankapplication.dto.response.account.AccountReadResponse;
import az.unitech.bankapplication.exception.AccountNotFoundException;
import az.unitech.bankapplication.exception.UnauthorizedAccessException;
import az.unitech.bankapplication.service.read.AccountReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class AccountReadController {

    private final AccountReadService accountReadService;

    @GetMapping("/all-accounts")
    public ResponseEntity<List<AccountReadResponse>> getAllAccounts(){
        List<AccountReadResponse> accountReadResponses = accountReadService.getAllAccounts();
        return ResponseEntity.ok(accountReadResponses);
    }

    @GetMapping("/my-account")
    public ResponseEntity<?> getMyAccount(Authentication authentication) {
        String username = authentication.getName();
        try {
            List<AccountReadResponse> account = accountReadService.getAccountsByUsername(username);
            return ResponseEntity.ok(account);
        } catch (UnauthorizedAccessException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
