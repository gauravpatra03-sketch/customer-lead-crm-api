package com.crm.customerlead.repository;

import com.crm.customerlead.entity.FollowUp;
import com.crm.customerlead.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FollowUpRepository extends JpaRepository<FollowUp, Long> {

    List<FollowUp> findByLeadId(Long leadId);

    @Query("SELECT f FROM FollowUp f WHERE DATE(f.nextFollowUpDate) = :date")
    List<FollowUp> findByNextFollowUpDate(@Param("date") LocalDate date);

    @Query("SELECT f FROM FollowUp f WHERE DATE(f.nextFollowUpDate) < :date AND f.status NOT IN ('CLOSED_WON', 'CLOSED_LOST', 'NOT_INTERESTED')")
    List<FollowUp> findOverdueFollowUps(@Param("date") LocalDate date);

    @Query("SELECT f FROM FollowUp f WHERE DATE(f.nextFollowUpDate) = :date AND f.status NOT IN ('CLOSED_WON', 'CLOSED_LOST', 'NOT_INTERESTED')")
    List<FollowUp> findTodayFollowUps(@Param("date") LocalDate date);

    @Query("SELECT f FROM FollowUp f WHERE DATE(f.nextFollowUpDate) = :date AND f.status NOT IN ('CLOSED_WON', 'CLOSED_LOST', 'NOT_INTERESTED')")
    List<FollowUp> findTomorrowFollowUps(@Param("date") LocalDate date);

    @Query("SELECT COUNT(f) FROM FollowUp f WHERE DATE(f.nextFollowUpDate) = :date AND f.status NOT IN ('CLOSED_WON', 'CLOSED_LOST', 'NOT_INTERESTED')")
    Long countTodayFollowUps(@Param("date") LocalDate date);

    @Query("SELECT COUNT(f) FROM FollowUp f WHERE DATE(f.nextFollowUpDate) < :date AND f.status NOT IN ('CLOSED_WON', 'CLOSED_LOST', 'NOT_INTERESTED')")
    Long countOverdueFollowUps(@Param("date") LocalDate date);

    @Query("SELECT COUNT(f) FROM FollowUp f WHERE DATE(f.nextFollowUpDate) > :date AND f.status NOT IN ('CLOSED_WON', 'CLOSED_LOST', 'NOT_INTERESTED')")
    Long countPendingFollowUps(@Param("date") LocalDate date);

    @Query("SELECT f FROM FollowUp f WHERE DATE(f.nextFollowUpDate) > :date AND f.status NOT IN ('CLOSED_WON', 'CLOSED_LOST', 'NOT_INTERESTED')")
    List<FollowUp> findUpcomingFollowUps(@Param("date") LocalDate date);

    List<FollowUp> findTop10ByOrderByCreatedDateDesc();
}
