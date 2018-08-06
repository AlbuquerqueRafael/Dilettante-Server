package com.api.dl.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import com.api.dl.user.User;
import com.api.dl.user.UserRepository;
import com.api.dl.user.UserService;
import com.api.dl.DLApiTests;
import com.api.dl.publication.Content;
import com.api.dl.publication.Location;
import com.api.dl.publication.Publication;
import com.api.dl.publication.PublicationRepository;
import com.api.dl.publication.PublicationService;
import com.api.dl.publication.Type;
import com.api.dl.publication.exceptions.PublicationNotFoundException;
import com.api.dl.publication.exceptions.PublicationOwnerException;
import com.api.dl.publication.love.LoveRepository;



public class PublicationServiceTests extends DLApiTests {


  @Rule
  public ExpectedException expectedEx = ExpectedException.none();

	@Autowired
  private PublicationRepository publicationRepository;
  
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private LoveRepository loveRepository;

  @Autowired
  private UserService userService;
  
  @Autowired
  private PublicationService publicationService;
  
  @Before
  public void beforeTest() {
    LocalDate ld = LocalDate.now();
    User user = new User("username1", "password1", null, "email1", true, ld);

    userService.signup(user);
  }

	@After
	public void afterTest() {
    this.loveRepository.deleteAll();
    this.publicationRepository.deleteAll();
		this.userRepository.deleteAll();

  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })

  public void create() {
    Location lc = new Location("additionalInfo","country",  "state",  "city");
    Publication publication = new Publication("publication1", new Content(Type.GENERAL, null, null, null), null, lc);
    Map<String, String> response =  publicationService.create(publication);


    assertThat(response.get("response")).isEqualTo("Publication created");

  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })

  public void getPublications() {
    Location lc = new Location("additionalInfo","country",  "state",  "city");
    Location lc1 = new Location("additionalInfo","country",  "state",  "city");
    Location lc2 = new Location("additionalInfo","country",  "state",  "city");
    Location lc3 = new Location("additionalInfo","country",  "state",  "city");

    Publication publication = new Publication("publication1", new Content(Type.GENERAL, null, null, null), null, lc);
    Publication publication1 = new Publication("publication2", new Content(Type.GENERAL, null, null, null), null, lc1);
    Publication publication2 = new Publication("publication3", new Content(Type.GENERAL, null, null, null), null, lc2);
    Publication publication3 = new Publication("publication4", new Content(Type.GENERAL, null, null, null), null, lc3);

    publicationService.create(publication);
    publicationService.create(publication1);
    publicationService.create(publication2);
    publicationService.create(publication3);


    Map<String, List<Publication>> response = publicationService.getPublications();

    assertThat(response.get("data").size()).isEqualTo(4);


  }

  @Test
  public void editWithInvalidID() {
    Location lc = new Location("additionalInfo","country",  "state",  "city");
    Publication publication = new Publication("publication1", new Content(Type.GENERAL, null, null, null), null, lc);

    expectedEx.expect(PublicationNotFoundException.class);
    expectedEx.expectMessage("Publication not found");

    publicationService.edit(50L, publication);

  
  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })
  public void editWithInvalidUser() {
    LocalDate ld = LocalDate.now();
    User user = new User("username2", "password2", null, "email2", true, ld);
    userService.signup(user);
    
    Location lc = new Location("additionalInfo","country",  "state",  "city");
    Publication publication = new Publication("publication1", new Content(Type.GENERAL, null, null, null), user, lc);
    publicationRepository.save(publication);

    expectedEx.expect(PublicationOwnerException.class);
    expectedEx.expectMessage("You are not the owner of this publication");

    publicationService.edit(publication.getId(), publication);

  
  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })
  public void edit() {
    Location lc = new Location("additionalInfo","country",  "state",  "city");
    Publication publication = new Publication("publication1", new Content(Type.GENERAL, null, null, null), null, lc);
    publicationService.create(publication);

    Publication publication2 = new Publication("publication2", new Content(Type.GENERAL, null, null, null), null, lc);

    Map<String, Publication> response = publicationService.edit(publication.getId(), publication2);

    assertThat(response.get("data").getName()).isEqualTo("publication2");
  }

  @Test

  public void getPublicationFail() {

    expectedEx.expect(PublicationNotFoundException.class);
    expectedEx.expectMessage("Publication not found");

    publicationService.getPublication(50L);
  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })

  public void getPublication() {
    Location lc = new Location("additionalInfo","country",  "state",  "city");
    Publication publication = new Publication("publication1", new Content(Type.GENERAL, null, null, null), null, lc);
    publicationService.create(publication);
    Map<String, Publication> response = publicationService.getPublication(publication.getId());


    assertThat(response.get("data").getName()).isEqualTo("publication1");


  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })

  public void lovePublication() {
    Location lc = new Location("additionalInfo","country",  "state",  "city");
    Publication publication = new Publication("publication1", new Content(Type.GENERAL, null, null, null), null, lc);
    publicationService.create(publication);
    Map<String, String> response = publicationService.lovePublication(publication.getId());


    assertThat(response.get("response")).isEqualTo("Publication loved");


  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })

  public void lovePublicationTwice() {
    Location lc = new Location("additionalInfo","country",  "state",  "city");
    Publication publication = new Publication("publication1", new Content(Type.GENERAL, null, null, null), null, lc);
    publicationService.create(publication);
    publicationService.lovePublication(publication.getId());
    Map<String, String> response = publicationService.lovePublication(publication.getId());


    assertThat(response.get("response")).isEqualTo("You already loved this publication");


  }


}