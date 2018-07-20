package com.api.dl.publication.upload;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;
import com.api.dl.exceptions.UploadException;
import com.api.dl.publication.Content;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobId;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UploadService {
  
  private static Storage storage = null;

  @PostConstruct
  void started() {
    try {
      StorageOptions storageOptions = StorageOptions.newBuilder()
      .setProjectId("dilettante-9c038")
      .setCredentials(GoogleCredentials.fromStream(new 
      FileInputStream("dilettante-9a7757878845.json"))).build();
      storage = storageOptions.getService();
    } catch(Exception e) {
      throw new UploadException(e.getMessage());
    }
   
  }

  public Content uploadFile(MultipartFile filePart, Content content) throws IOException{
    Instant now = Instant.now();

    final String fileName = filePart.getOriginalFilename() + now.toString();
    deleteFile("dilettante-9c038.appspot.com", content);
    content.setBlobName(fileName);
    content.setLink(this.uploadFile(filePart, "dilettante-9c038.appspot.com", fileName));

    return content;
  }
  
  private String uploadFile(MultipartFile filePart, final String bucketName, String fileName) throws IOException {
    BlobInfo blobInfo;

    try {
      blobInfo = storage.create(BlobInfo.newBuilder(bucketName, fileName)
                              .setContentType(filePart.getContentType())
                              // Modify access list to allow all users with link to read file
                              .setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER))))
                              .build(),
          filePart.getInputStream());
      return blobInfo.getMediaLink();
    } catch(Exception e) {
      throw new UploadException(e.getMessage());
    }
   
  }

  private void deleteFile(String bucketName, Content content) {
    if (content.getLink() != null) {
      BlobId blobId = BlobId.of(bucketName, content.getBlobName());
      boolean deleted = storage.delete(blobId);
    }
  }
}