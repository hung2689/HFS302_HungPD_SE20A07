package com.example.CRUDbasis.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
      String id ;
      String username;
      String fullname;
      String password;
       LocalDate dob;
}
