package com.api.dl.publication.love;

import java.util.List;

import javax.transaction.Transactional;

import com.api.dl.publication.Publication;
import com.api.dl.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface LoveRepository extends JpaRepository<Love, Long> {
  List<Love> findByPublicationAndUser(Publication publication, User user);
}