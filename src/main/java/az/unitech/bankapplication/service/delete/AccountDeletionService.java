package az.unitech.bankapplication.service.delete;

import az.unitech.bankapplication.entity.AccountEntity;
import az.unitech.bankapplication.entity.UserEntity;
import az.unitech.bankapplication.repository.UserRepository;
import az.unitech.bankapplication.service.login.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountDeletionService {

    private final UserRepository userRepository;
    private final UserLoginService userLoginService;

    public ResponseEntity<?> deleteAccount(String username, String password, String accountNumber) {

        if (userLoginService.authenticate(username, password)) {

            UserEntity user = userRepository.findByUserName(username);
            if (!user.getPassword().equals(password)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Kohne Sifre Yanlisdir");
            }

            AccountEntity accountToDelete = findAccountByAccountNumber(user, accountNumber);
            if (accountToDelete == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hesab tapilmadi");
            }

            if (!confirmAccountDeletion(username, accountToDelete)) {
                return ResponseEntity.ok("Hesab silme redd edildi");
            }

            accountToDelete.setCardStatus("inactive");
            userRepository.save(user);

            return ResponseEntity.ok("Hesab ugurlu sekilde deactive oldu");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Istifadeci adi veya Sifre yanlisdir");
        }
    }

    public AccountEntity findAccountByAccountNumber(UserEntity user, String accountNumber) {

        List<AccountEntity> userAccounts = user.getAccounts();
        for (AccountEntity account : userAccounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    private boolean confirmAccountDeletion(String username, AccountEntity accountToDelete) {

        return true;
    }
}