package com.api.dl.thread;

import java.util.List;

import javax.transaction.Transactional;

import com.api.dl.publication.Publication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ThreadRepository extends JpaRepository<Thread, Long> {
  
  List<Thread> findByPublication(Publication publication);
}