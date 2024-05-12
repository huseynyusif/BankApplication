package az.unitech.bankapplication.dto.request.user;

import az.unitech.bankapplication.validation.ValidPassword;
import az.unitech.bankapplication.validation.ValidPin;
import az.unitech.bankapplication.validation.ValidUsername;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @ValidUsername
    private String userName;

    @NotBlank
    @ValidPassword
    private String password;

    @NotBlank
    @ValidPin
    private String pin;

}
