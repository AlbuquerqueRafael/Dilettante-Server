package com.api.dl.publication;



import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PublicationRepository extends JpaRepository<Publication, Long> {
  
  Page<Publication> findAll(Pageable pageable);
}