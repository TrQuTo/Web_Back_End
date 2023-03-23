package com.tqt.WebBasic.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
  private String firstname;
  private String lastname;
  private String password;
  private String phone;
  private String email;
  private String birthday;
  private String registrationDate;
  private String imageUrl;
}
