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
import com.api.dl.reply.Reply;
import com.api.dl.reply.ReplyRepository;
import com.api.dl.reply.ReplyService;
import com.api.dl.thread.Thread;
import com.api.dl.thread.ThreadRepository;
import com.api.dl.thread.ThreadService;
import com.api.dl.thread.ThreadType;
import com.api.dl.thread.exceptions.ThreadNotFoundException;



public class ReplyServiceTests extends DLApiTests {


  @Rule
  public ExpectedException expectedEx = ExpectedException.none();

	@Autowired
  private PublicationRepository publicationRepository;

  @Autowired
  private ThreadRepository threadRepository;

  @Autowired
  private ThreadService threadService;

  @Autowired
  private ReplyService replyService;
  
  @Autowired
  private ReplyRepository replyRepository;

  @Autowired
  private UserRepository userRepository;


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
    this.replyRepository.deleteAll();
    this.threadRepository.deleteAll();
    this.publicationRepository.deleteAll();
		this.userRepository.deleteAll();

  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })

  public void save() {
    Location lc = new Location("additionalInfo","country",  "state",  "city");
    Publication publication = new Publication("publication1", new Content(Type.GENERAL, null, null, null), null, lc);
    publicationService.create(publication);

    Thread thread = new Thread("comment", null, publication, ThreadType.FEELINGS);
    thread = threadService.save(publication.getId(), thread).get("data");

    Reply reply = new Reply("comment1", null, thread);
    Map<String, Reply> response = replyService.save(thread.getId(), reply);

    assertThat(response.get("data").getComment()).isEqualTo("comment1");

  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })

  public void saveWithInvalidThread() {
    Reply reply = new Reply("comment1", null, null);
    expectedEx.expect(ThreadNotFoundException.class);
    expectedEx.expectMessage("Thread not found");

    replyService.save(50L, reply);
  }


  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })
  public void getRepliesByThreadFail() {
    expectedEx.expect(ThreadNotFoundException.class);
    expectedEx.expectMessage("Thread not found");

    replyService.getRepliesByThread(50L);

  }

  @Test
  @WithMockUser(username = "username1", authorities = { "ADMIN", "USER" })
  public void getThreadsByPublication() {
    Location lc = new Location("additionalInfo","country",  "state",  "city");
    Publication publication = new Publication("publication1", new Content(Type.GENERAL, null, null, null), null, lc);
    publicationService.create(publication);
    Thread thread = new Thread("comment", null, publication, ThreadType.FEELINGS);
    thread = threadService.save(publication.getId(), thread).get("data");

    Reply reply1 = new Reply("comment1", null, thread);
    Reply reply2 = new Reply("comment2", null, thread);

    replyService.save(thread.getId(), reply1);
    replyService.save(thread.getId(), reply2);

    Map<String, List<Reply>> response = replyService.getRepliesByThread(thread.getId());


    assertThat(response.get("data").size()).isEqualTo(2);

  }


}