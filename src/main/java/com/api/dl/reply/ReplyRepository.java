package com.api.dl.reply;

import java.util.List;

import javax.transaction.Transactional;

import com.api.dl.thread.Thread;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ReplyRepository extends JpaRepository<Reply, Long> {
  List<Reply> findByThread(Thread thread);

}