package az.unitech.bankapplication.mapper;

import az.unitech.bankapplication.dto.request.user.UserCreateRequest;
import az.unitech.bankapplication.dto.response.user.UserCreateResponse;
import az.unitech.bankapplication.dto.response.user.UserLoginResponse;
import az.unitech.bankapplication.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserCreateRequest createRequestToEntity(UserEntity user);

    UserEntity entityToCreateRequest(UserCreateRequest userCreateRequest);

    UserCreateResponse entityToCreateResponse(UserEntity user);

    UserLoginResponse entityToLoginResponse(UserEntity user);
}
