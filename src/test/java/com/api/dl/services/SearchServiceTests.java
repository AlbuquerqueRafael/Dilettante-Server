package com.api.dl.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
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
import com.api.dl.search.SearchService;



public class SearchServiceTests extends DLApiTests {


  @Rule
  public ExpectedException expectedEx = ExpectedException.none();

	@Autowired
  private PublicationRepository publicationRepository;
  
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userService;
  
  @Autowired
  private PublicationService publicationService;

  @Autowired
  private SearchService searchService;
  
  @Before
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })

  public void beforeTest() {
    LocalDate ld = LocalDate.now();
    User user = new User("username1", "password1", null, "email1", true, ld);

    userService.signup(user);

    Location lc = new Location("additionalInfo","country1",  "state1",  "city1");
    Location lc1 = new Location("additionalInfo","country2",  "state2",  "city2");
    Location lc2 = new Location("additionalInfo","country3",  "state3",  "city3");
    Location lc3 = new Location("additionalInfo","country4",  "state4",  "city4");

    Publication publication = new Publication("publication1", new Content(Type.GENERAL, null, null, null), null, lc);
    Publication publication1 = new Publication("publication2", new Content(Type.GENERAL, null, null, null), null, lc1);
    Publication publication2 = new Publication("publication3", new Content(Type.POETRY, null, null, null), null, lc2);
    Publication publication3 = new Publication("publication4", new Content(Type.SCULPTURE, null, null, null), null, lc3);

    publicationService.create(publication);
    publicationService.create(publication1);
    publicationService.create(publication2);
    publicationService.create(publication3);



  }

	@After
	public void afterTest() {
    this.publicationRepository.deleteAll();
		this.userRepository.deleteAll();

  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })
  public void searchByName() {
    Map<String, String[]> searchParams = new HashMap<String, String[]>();
    searchParams.put("option", new String[]{"publication"});
    searchParams.put("p", new String[]{"0"});
    searchParams.put("timeFilter", new String[]{"2"});

    searchParams.put("name", new String[]{"publication1"});
    Map<String, List<Publication>> response = searchService.search(searchParams);


    assertThat(response.get("data").size()).isEqualTo(1);
    assertThat(response.get("data").get(0).getName()).isEqualTo("publication1");

  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })
  public void searchByName2() {
    Map<String, String[]> searchParams = new HashMap<String, String[]>();
    searchParams.put("option", new String[]{"publication"});
    searchParams.put("p", new String[]{"0"});
    searchParams.put("timeFilter", new String[]{"2"});

    searchParams.put("name", new String[]{"publication"});
    Map<String, List<Publication>> response = searchService.search(searchParams);


    assertThat(response.get("data").size()).isEqualTo(4);

  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })
  public void searchByCountry() {
    Map<String, String[]> searchParams = new HashMap<String, String[]>();
    searchParams.put("option", new String[]{"publication"});
    searchParams.put("p", new String[]{"0"});
    searchParams.put("timeFilter", new String[]{"2"});
    searchParams.put("country", new String[]{"country1"});

    Map<String, List<Publication>> response = searchService.search(searchParams);


    assertThat(response.get("data").size()).isEqualTo(1);
    assertThat(response.get("data").get(0).getName()).isEqualTo("publication1");

  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })
  public void searchByCountry2() {
    Map<String, String[]> searchParams = new HashMap<String, String[]>();
    searchParams.put("option", new String[]{"publication"});
    searchParams.put("p", new String[]{"0"});
    searchParams.put("timeFilter", new String[]{"2"});
    searchParams.put("country", new String[]{"country"});

    Map<String, List<Publication>> response = searchService.search(searchParams);


    assertThat(response.get("data").size()).isEqualTo(4);

  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })
  public void searchByState() {
    Map<String, String[]> searchParams = new HashMap<String, String[]>();
    searchParams.put("option", new String[]{"publication"});
    searchParams.put("p", new String[]{"0"});
    searchParams.put("timeFilter", new String[]{"2"});
    searchParams.put("state", new String[]{"state1"});

    Map<String, List<Publication>> response = searchService.search(searchParams);


    assertThat(response.get("data").size()).isEqualTo(1);
    assertThat(response.get("data").get(0).getName()).isEqualTo("publication1");

  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })
  public void searchByState2() {
    Map<String, String[]> searchParams = new HashMap<String, String[]>();
    searchParams.put("option", new String[]{"publication"});
    searchParams.put("p", new String[]{"0"});
    searchParams.put("timeFilter", new String[]{"2"});
    searchParams.put("state", new String[]{"state"});

    Map<String, List<Publication>> response = searchService.search(searchParams);


    assertThat(response.get("data").size()).isEqualTo(4);

  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })
  public void searchByCity() {
    Map<String, String[]> searchParams = new HashMap<String, String[]>();
    searchParams.put("option", new String[]{"publication"});
    searchParams.put("p", new String[]{"0"});
    searchParams.put("timeFilter", new String[]{"2"});
    searchParams.put("city", new String[]{"city1"});

    Map<String, List<Publication>> response = searchService.search(searchParams);


    assertThat(response.get("data").size()).isEqualTo(1);
    assertThat(response.get("data").get(0).getName()).isEqualTo("publication1");

  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })
  public void searchByCity2() {
    Map<String, String[]> searchParams = new HashMap<String, String[]>();
    searchParams.put("option", new String[]{"publication"});
    searchParams.put("p", new String[]{"0"});
    searchParams.put("timeFilter", new String[]{"2"});
    searchParams.put("city", new String[]{"city"});

    Map<String, List<Publication>> response = searchService.search(searchParams);


    assertThat(response.get("data").size()).isEqualTo(4);

  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })
  public void searchByType() {
    Map<String, String[]> searchParams = new HashMap<String, String[]>();
    searchParams.put("option", new String[]{"publication"});
    searchParams.put("p", new String[]{"0"});
    searchParams.put("timeFilter", new String[]{"2"});
    searchParams.put("type", new String[]{"0"});

    Map<String, List<Publication>> response = searchService.search(searchParams);


    assertThat(response.get("data").size()).isEqualTo(1);
    assertThat(response.get("data").get(0).getName()).isEqualTo("publication3");

  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })
  public void searchByNameAndType() {
    Map<String, String[]> searchParams = new HashMap<String, String[]>();
    searchParams.put("option", new String[]{"publication"});
    searchParams.put("p", new String[]{"0"});
    searchParams.put("timeFilter", new String[]{"2"});
    searchParams.put("name", new String[]{"publication3"});
    searchParams.put("type", new String[]{"0"});

    Map<String, List<Publication>> response = searchService.search(searchParams);


    assertThat(response.get("data").size()).isEqualTo(1);
    assertThat(response.get("data").get(0).getName()).isEqualTo("publication3");

  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })
  public void searchByNameAndType2() {
    Map<String, String[]> searchParams = new HashMap<String, String[]>();
    searchParams.put("option", new String[]{"publication"});
    searchParams.put("p", new String[]{"0"});
    searchParams.put("timeFilter", new String[]{"2"});
    searchParams.put("name", new String[]{"publication"});
    searchParams.put("type", new String[]{"0"});

    Map<String, List<Publication>> response = searchService.search(searchParams);


    assertThat(response.get("data").size()).isEqualTo(1);
    assertThat(response.get("data").get(0).getName()).isEqualTo("publication3");

  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })
  public void searchByNameAndType3() {
    Map<String, String[]> searchParams = new HashMap<String, String[]>();
    searchParams.put("option", new String[]{"publication"});
    searchParams.put("p", new String[]{"0"});
    searchParams.put("timeFilter", new String[]{"2"});
    searchParams.put("name", new String[]{"publication"});
    searchParams.put("type", new String[]{"5"});

    Map<String, List<Publication>> response = searchService.search(searchParams);


    assertThat(response.get("data").size()).isEqualTo(2);

  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })
  public void search() {
    Map<String, String[]> searchParams = new HashMap<String, String[]>();
    searchParams.put("option", new String[]{"publication"});
    searchParams.put("p", new String[]{"0"});
    searchParams.put("timeFilter", new String[]{"2"});
    searchParams.put("name", new String[]{"publication"});
    searchParams.put("type", new String[]{"5"});
    searchParams.put("city", new String[]{"city"});
    searchParams.put("country", new String[]{"country"});
    searchParams.put("state", new String[]{"state"});

    Map<String, List<Publication>> response = searchService.search(searchParams);


    assertThat(response.get("data").size()).isEqualTo(2);

  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })
  public void search1() {
    Map<String, String[]> searchParams = new HashMap<String, String[]>();
    searchParams.put("option", new String[]{"publication"});
    searchParams.put("p", new String[]{"0"});
    searchParams.put("timeFilter", new String[]{"2"});
    searchParams.put("name", new String[]{"publication"});
    searchParams.put("type", new String[]{"5"});
    searchParams.put("city", new String[]{"city"});
    searchParams.put("country", new String[]{"country"});
    searchParams.put("state", new String[]{"notvalid"});

    Map<String, List<Publication>> response = searchService.search(searchParams);


    assertThat(response.get("data").size()).isEqualTo(0);

  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })
  public void search2() {
    Map<String, String[]> searchParams = new HashMap<String, String[]>();
    searchParams.put("option", new String[]{"publication"});
    searchParams.put("p", new String[]{"0"});
    searchParams.put("timeFilter", new String[]{"2"});
    searchParams.put("name", new String[]{"publication55"});
    searchParams.put("type", new String[]{"5"});
    searchParams.put("city", new String[]{"city"});
    searchParams.put("country", new String[]{"country"});
    searchParams.put("state", new String[]{"state"});

    Map<String, List<Publication>> response = searchService.search(searchParams);


    assertThat(response.get("data").size()).isEqualTo(0);

  }
}