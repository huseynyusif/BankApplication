package az.unitech.bankapplication.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserReadResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
