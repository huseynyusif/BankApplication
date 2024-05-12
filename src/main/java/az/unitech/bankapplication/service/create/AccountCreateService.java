package az.unitech.bankapplication.service.create;

import az.unitech.bankapplication.dto.request.account.AccountCreateRequest;
import az.unitech.bankapplication.dto.response.account.AccountCreateResponse;
import az.unitech.bankapplication.entity.AccountEntity;
import az.unitech.bankapplication.entity.CurrencyRateEntity;
import az.unitech.bankapplication.entity.TransactionEntity;
import az.unitech.bankapplication.entity.UserEntity;
import az.unitech.bankapplication.mapper.AccountMapper;
import az.unitech.bankapplication.repository.AccountRepository;
import az.unitech.bankapplication.repository.CurrencyRateRepository;
import az.unitech.bankapplication.repository.TransactionRepository;
import az.unitech.bankapplication.repository.UserRepository;
import az.unitech.bankapplication.service.update.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountCreateService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final ExchangeService exchangeService;
    private final CurrencyRateRepository currencyRateRepository;


    public ResponseEntity<?> createAccount(AccountCreateRequest accountCreateRequest) {

        AccountEntity account = accountMapper.createRequestToEntity(accountCreateRequest);

        account.setCreationDate(LocalDate.now());
        account.setCardStatus("active");

        UserEntity user = userRepository.findById(accountCreateRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Istifadeci tapilmadi"));

        account.setUser(user);

        String selectedCurrencyPair = accountCreateRequest.getCurrency();

        CurrencyRateEntity currencyRate = currencyRateRepository.findByCurrencyPair(selectedCurrencyPair);

        if (currencyRate == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Zehmet olmasa mezenneyi duzgun daxil edin");
        }

        account.setCurrency(currencyRate.getCurrencyPair());

        LocalDate expirationDate = LocalDate.now().plusYears(3);
        account.setExpirationDate(expirationDate);

        AccountEntity createdAccount = accountRepository.save(account);

        if (expirationDate.isBefore(LocalDate.now())) {
            createdAccount.setCardStatus("inActive");
        }

        AccountCreateResponse responseDTO = accountMapper.entityToCreateResponse(createdAccount);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }


    public void transfer(Long senderAccountId, Long receiverAccountId, double amount) {
        AccountEntity senderAccount = accountRepository.findById(senderAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Gönderen hesab tapilmadi"));

        AccountEntity receiverAccount = accountRepository.findById(receiverAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Alan hesab tapilmadi"));

        if (senderAccountId.equals(receiverAccountId)) {
            throw new IllegalArgumentException("Oz hesabiniza pul gondere bilmezsiz");
        }

        if (!senderAccount.getCardStatus().equals("active")) {
            throw new IllegalStateException("Gönderen hesabın kartı aktiv deyil");
        }
        if (!receiverAccount.getCardStatus().equals("active")) {
            throw new IllegalStateException("Alan hesabın kartı aktiv deyil");
        }

        double senderBalance = senderAccount.getBalance();
        if (senderBalance < amount) {
            throw new IllegalArgumentException("Hesabinizda lazimi qeder balans yoxdur");
        }

        if (!senderAccount.getCurrency().equals(receiverAccount.getCurrency())) {
            amount = exchangeService.convertCurrency(senderAccount.getCurrency(), receiverAccount.getCurrency(), amount);
        }
        senderAccount.setBalance(senderBalance - amount);

        double receiverBalance = receiverAccount.getBalance();
        receiverAccount.setBalance(receiverBalance + amount);

        TransactionEntity transaction = new TransactionEntity();
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setAmount(amount);
        transaction.setSenderAccount(senderAccount);
        transaction.setReceiverAccount(receiverAccount);
        transactionRepository.save(transaction);
    }
}
