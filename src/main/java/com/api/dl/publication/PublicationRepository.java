package com.api.dl.publication;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PublicationRepository extends JpaRepository<Publication, Long> {
  
	public Page<Publication> findAll(Specification<?> spec, Pageable page);
}