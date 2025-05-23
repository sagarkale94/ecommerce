package co.in.sagarkale.ecommerce.dtos;

import co.in.sagarkale.ecommerce.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {

  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private Role role;
}
