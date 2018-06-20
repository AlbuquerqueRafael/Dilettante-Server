package com.api.dl.search;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.api.dl.publication.Content;
import com.api.dl.publication.Location;
import com.api.dl.publication.Publication;
import com.api.dl.search.dto.PublicationSearchDTO;

import org.springframework.data.jpa.domain.Specification;

public class PublicationSearchSpecifications implements Specification<Publication> {
  private static final long serialVersionUID = -17851316990372L;
  private PublicationSearchDTO filter;

  public PublicationSearchSpecifications (PublicationSearchDTO filter) {
      super();
      this.filter = filter;
  }

  public Predicate toPredicate(Root<Publication> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

      //Predicate predicate = cb.disjunction();
      List<Predicate> predicates = new ArrayList<Predicate>();
      Join<Publication, Location> locationJoin = root.join("location");
      Join<Publication, Content> contentJoin = root.join("content");

      if (filter.getType() != null) {
        predicates.add(cb.equal(contentJoin.get("type"), filter.getType().getValue()));
      }

      if (filter.getLocation().getCity() != null) {
        String city = filter.getLocation().getCity();
        predicates.add(cb.like(cb.lower(locationJoin.<String> get("city")), 
                                              "%" + city.toLowerCase() + "%"));

      }

      if (filter.getLocation().getState() != null) {
        String state = filter.getLocation().getState();
        predicates.add(cb.like(cb.lower(locationJoin.<String> get("state")), 
                                                "%" + state + "%"));
      }

      if (filter.getLocation().getCountry() != null) {
        String country = filter.getLocation().getCountry();
        predicates.add(cb.like(cb.lower(locationJoin.<String> get("country")), 
                                                "%" + country + "%"));
      }

      if (filter.getTimeFilter().equals(TimeFilter.THIS_MONTH)) {
        LocalDate now = LocalDate.now();
        LocalDate firstDayMonth = now.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayMonth = now.with(TemporalAdjusters.lastDayOfMonth());

        LocalDateTime firstDayMonthDateTime = firstDayMonth.atStartOfDay();
        LocalDateTime lastDayMonthDateTime = lastDayMonth.atTime(LocalTime.MAX);

        predicates.add(cb.between(root.<LocalDateTime>get("createdAt"),
                                  cb.literal(firstDayMonthDateTime),
                                  cb.literal(lastDayMonthDateTime)));
      }

      if (filter.getTimeFilter().equals(TimeFilter.THIS_YEAR)) {
        LocalDate now = LocalDate.now();
        LocalDate firstDayYear = now.with(TemporalAdjusters.firstDayOfYear());
        LocalDate lastDayYear = now.with(TemporalAdjusters.lastDayOfYear());

        LocalDateTime firstDayYearDateTime = firstDayYear.atStartOfDay();
        LocalDateTime lastDayYearDateTime = lastDayYear.atTime(LocalTime.MAX);

        predicates.add(cb.between(root.<LocalDateTime>get("createdAt"),
                       cb.literal(firstDayYearDateTime),
                       cb.literal(lastDayYearDateTime)));
      }

      cq.distinct(true);

      return cb.and(predicates.toArray(new Predicate[predicates.size()]));

  }

}