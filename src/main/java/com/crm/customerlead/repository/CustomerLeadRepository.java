package com.crm.customerlead.repository;

import com.crm.customerlead.entity.CustomerLead;
import com.crm.customerlead.entity.Priority;
import com.crm.customerlead.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerLeadRepository extends JpaRepository<CustomerLead, Long> {

    List<CustomerLead> findByCustomerNameContainingIgnoreCase(String customerName);

    List<CustomerLead> findByMobileContaining(String mobile);

    List<CustomerLead> findByCityContainingIgnoreCase(String city);

    List<CustomerLead> findByLeadTypeId(Long leadTypeId);

    List<CustomerLead> findByStatus(Status status);

    List<CustomerLead> findByPriority(Priority priority);

    List<CustomerLead> findByAssignedExecutiveContainingIgnoreCase(String executive);

    @Query("SELECT c FROM CustomerLead c WHERE c.createdDate BETWEEN :startDate AND :endDate")
    List<CustomerLead> findByCreatedDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(c) FROM CustomerLead c")
    Long countTotalLeads();

    @Query("SELECT COUNT(c) FROM CustomerLead c WHERE DATE(c.createdDate) = :date")
    Long countByCreatedDate(@Param("date") LocalDate date);

    @Query("SELECT COUNT(c) FROM CustomerLead c WHERE c.status = :status")
    Long countByStatus(@Param("status") Status status);

    @Query("SELECT COUNT(c) FROM CustomerLead c WHERE c.priority = :priority")
    Long countByPriority(@Param("priority") Priority priority);

    @Query("SELECT COUNT(c) FROM CustomerLead c WHERE YEAR(c.createdDate) = :year AND MONTH(c.createdDate) = :month")
    Long countByMonth(@Param("year") int year, @Param("month") int month);

    @Query("SELECT c FROM CustomerLead c WHERE " +
           "(:customerName IS NULL OR LOWER(c.customerName) LIKE LOWER(CONCAT('%', :customerName, '%'))) AND " +
           "(:mobile IS NULL OR c.mobile LIKE CONCAT('%', :mobile, '%')) AND " +
           "(:city IS NULL OR LOWER(c.city) LIKE LOWER(CONCAT('%', :city, '%'))) AND " +
           "(:leadTypeId IS NULL OR c.leadType.id = :leadTypeId) AND " +
           "(:status IS NULL OR c.status = :status) AND " +
           "(:priority IS NULL OR c.priority = :priority) AND " +
           "(:executive IS NULL OR LOWER(c.assignedExecutive) LIKE LOWER(CONCAT('%', :executive, '%'))) AND " +
           "(:startDate IS NULL OR c.createdDate >= :startDate) AND " +
           "(:endDate IS NULL OR c.createdDate <= :endDate)")
    List<CustomerLead> searchLeads(
            @Param("customerName") String customerName,
            @Param("mobile") String mobile,
            @Param("city") String city,
            @Param("leadTypeId") Long leadTypeId,
            @Param("status") Status status,
            @Param("priority") Priority priority,
            @Param("executive") String executive,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    List<CustomerLead> findTop10ByOrderByCreatedDateDesc();
}
