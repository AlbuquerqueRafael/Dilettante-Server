package com.api.dl.thread;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ThreadRepository extends JpaRepository<Thread, Long> {
  
}