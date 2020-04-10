package com.gaf.reminderserver.dao;

import com.gaf.reminderserver.entities.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantsDao extends JpaRepository<Tenants,Long> {
    @Modifying
    @Query(value = "update rem_tenants set is_paid = false",nativeQuery = true)
    void doUpdate();

    List<Tenants> findByIsPaid(boolean b);
}
