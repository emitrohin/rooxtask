package ru.emitrohin.roox.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import ru.emitrohin.roox.model.PartnerMapping;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by emitrokhin on 28.02.2017.
 */
public interface PartnerMappingRepository extends Repository<PartnerMapping, Integer> {

    @Query("SELECT p FROM PartnerMapping p JOIN FETCH p.customer WHERE p.id = ?1 and p.customer.id = ?2")
    PartnerMapping findOne(int id, int customerId);

    List<PartnerMapping> findAllByCustomerId(int customerId);

    @Transactional
    PartnerMapping save(PartnerMapping partnerMapping);

    @Transactional
    @Modifying
    @Query("DELETE FROM PartnerMapping p WHERE p.id= ?1 AND p.customer.id= ?2")
    int delete(int id, int customerId);
}

