package com.api.dl.publication;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.api.dl.exceptions.UploadException;
import com.api.dl.publication.upload.UploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@CrossOrigin
public class PublicationController {

  @Autowired
  private PublicationService publicationService;

  @Autowired
  private PublicationValidator publicationValidator;

  @Autowired
  private UploadService uploadService;

  @RequestMapping(value = "/publication/{id}", method = RequestMethod.PUT)
  public Map<String, Publication> edit (@PathVariable("id") Long id, @RequestBody Publication publication) {
    publicationValidator.validateCreateAndEdit(publication);
    Map<String, Publication> response = publicationService.edit(id, publication);

		return response;
  }

  @RequestMapping(value = "/publication/{id}/love", method = RequestMethod.POST)
  public Map<String, String> edit (@PathVariable("id") Long id) {
    Map<String, String> response = publicationService.lovePublication(id);

		return response;
  }

  @RequestMapping(value = "/publication", method = RequestMethod.POST)
  public Map<String, String> create (@RequestBody Publication publication) {
    publicationValidator.validateCreateAndEdit(publication);
    Map<String, String> response = publicationService.create(publication);

		return response;
  }

  @RequestMapping(value = "/publication", method = RequestMethod.GET)
  public Map<String, List<Publication>> getPublications () {
    Map<String, List<Publication>> response = publicationService.getPublications();

		return response;
  }

  @RequestMapping(value = "/publication/{id}", method = RequestMethod.GET)
  public Map<String, Publication> getPublication (@PathVariable("id") Long id) {
    Map<String, Publication> response = publicationService.getPublication(id);

		return response;
  }


  @RequestMapping(value = "/publication/{id}/uploadContent", method = RequestMethod.POST)
  public Map<String, Publication> uploadContent (@RequestParam("file") MultipartFile file,
                                               @PathVariable("id") Long id) throws IOException {
    Publication publication = publicationService.getPublicationByID(id);
    Content content = uploadService.uploadFile(file, publication.getContent());
    publication.setContent(content);
    publicationService.persistContent(publication);

		return new HashMap<String, Publication>();
  }



}
