package com.api.dl.reply;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ReplyRepository extends JpaRepository<Reply, Long> {
  
}