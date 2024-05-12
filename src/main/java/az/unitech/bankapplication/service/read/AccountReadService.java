package az.unitech.bankapplication.service.read;

import az.unitech.bankapplication.dto.response.account.AccountReadResponse;
import az.unitech.bankapplication.entity.AccountEntity;
import az.unitech.bankapplication.exception.AccountNotFoundException;
import az.unitech.bankapplication.exception.UnauthorizedAccessException;
import az.unitech.bankapplication.mapper.AccountMapper;
import az.unitech.bankapplication.repository.AccountRepository;
import az.unitech.bankapplication.service.login.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountReadService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;


    public List<AccountReadResponse> getAllAccounts() {
        List<AccountEntity> accountEntities = accountRepository.findAll();
        return accountEntities.stream().map(accountMapper::entityToReadResponse).collect(Collectors.toList());
    }


    public List<AccountReadResponse> getAccountsByUsername(String username) {
        List<AccountEntity> accountEntities = accountRepository.findByUser_UserName(username);
        return accountEntities.stream().map(accountMapper::entityToReadResponse).collect(Collectors.toList());
    }
}
