package az.unitech.bankapplication.mapper;

import az.unitech.bankapplication.dto.request.account.AccountCreateRequest;
import az.unitech.bankapplication.dto.response.account.AccountCreateResponse;
import az.unitech.bankapplication.dto.response.account.AccountReadResponse;
import az.unitech.bankapplication.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(source = "userId", target = "user.id")
    AccountEntity createRequestToEntity(AccountCreateRequest accountCreateRequest);

    @Mapping(source = "user.id", target = "userId")
    AccountCreateResponse entityToCreateResponse(AccountEntity accountEntity);

    @Mapping(source = "user.id", target = "userId")
    AccountReadResponse entityToReadResponse(AccountEntity accountEntity);


}
