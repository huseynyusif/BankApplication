package az.unitech.bankapplication.mapper;

import az.unitech.bankapplication.dto.request.user.UserCreateRequest;
import az.unitech.bankapplication.dto.response.user.UserCreateResponse;
import az.unitech.bankapplication.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserCreateRequest createRequestToEntity(User user);

    User entityToCreateRequest(UserCreateRequest userCreateRequest);

    UserCreateResponse entityToCreateResponse(User user);
}
