package com.tqt.WebBasic.auth;

import com.tqt.WebBasic.model.User;
import com.tqt.WebBasic.repository.UserRepository;
import com.tqt.WebBasic.service.IGeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  private final IGeneralService iGeneralService;
  private final AuthenticationService service;
  private final UserRepository userRepository;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
    boolean emailFound = userRepository.existsUserByEmail(request.getEmail());
    if(emailFound){
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is exist");
    }
    request.setRegistrationDate(iGeneralService.dateNow("yyyy-MM-dd hh:mm:ss"));
    return ResponseEntity.ok(service.register(request).toString());
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }


}
