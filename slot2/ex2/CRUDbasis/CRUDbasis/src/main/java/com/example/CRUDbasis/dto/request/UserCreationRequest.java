package com.example.CRUDbasis.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min=6,message = "USERNAME_INVAILD")
      String username;
      String fullname;
    @Size(min = 8, message = "PASSWORD_INVAILD")
      String password;
      LocalDate dob;

}
