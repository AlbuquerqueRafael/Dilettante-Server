package com.api.dl.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.dl.user.User;
import com.api.dl.user.UserRepository;
import com.api.dl.user.UserService;
import com.api.dl.user.exceptions.InvalidCredentialsException;
import com.api.dl.DLApiTests;



public class UserServiceTests extends DLApiTests {

  @Rule
  public ExpectedException expectedEx = ExpectedException.none();

	@Autowired
  private UserRepository userRepository;
  
  @Autowired
	private UserService userService;

  @Before
  public void beforeTest() {
    LocalDate ld = LocalDate.now();
    User user = new User("username1", "password1", null, "email1", true, ld);
    
    userService.signup(user);
  }

	@After
	public void afterTest() {
		this.userRepository.deleteAll();
  }

  @Test
  public void loginWithEmptyUsername() {
    expectedEx.expect(InvalidCredentialsException.class);
    expectedEx.expectMessage("Please, provide a valid username and password");

    userService.login("", "qualquer1");
  }


  @Test
  public void loginWithNullUsername() {
    expectedEx.expect(InvalidCredentialsException.class);
    expectedEx.expectMessage("Please, provide a valid username and password");

    userService.login(null, "qualquer1");
  }

  @Test
  public void loginWithInvalidUsername() {
    expectedEx.expect(InvalidCredentialsException.class);
    expectedEx.expectMessage("Please, provide a valid username and password");

    userService.login("zezinho", "qualquer1");
  }


  @Test
  public void loginEmptyPassword() {
    expectedEx.expect(InvalidCredentialsException.class);
    expectedEx.expectMessage("Please, provide a valid username and password");

    userService.login("username1", "");
  }

  @Test
  public void loginWithInvalidPassword() {
    expectedEx.expect(InvalidCredentialsException.class);
    expectedEx.expectMessage("Please, provide a valid username and password");

    userService.login("username1", "qualquer1");
  }


  @Test
  public void login() {
    Map<String, String> response = userService.login("username1", "password1");

    assertThat(response.get("response")).isEqualTo("Login worked");
  }

  @Test
  public void signup() {
    LocalDate ld = LocalDate.now();
    User user = new User("username2", "password2", null, "email2", true, ld);
    Map<String, String> response = userService.signup(user);

    assertThat(response.get("response")).isEqualTo("Sign up worked");
  }
	
}