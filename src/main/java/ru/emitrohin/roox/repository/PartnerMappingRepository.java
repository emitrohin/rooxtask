package ru.emitrohin.roox.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import ru.emitrohin.roox.model.PartnerMapping;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Partner mappings repository interface. Uses spring data {@code Repository} interface to execute queries based on method
 * naming convention
 *
 * @author Evgeniy Mitrokhin
 */
public interface PartnerMappingRepository extends Repository<PartnerMapping, Integer> {

    /**
     * Select mapping from partner_mapping table by mapping id and customer id
     *
     * @param id - mapping id
     * @param customerId - id of customer
     * @return Partner mapping from database
     */
    @Query("SELECT p FROM PartnerMapping p JOIN FETCH p.customer WHERE p.id = ?1 and p.customer.id = ?2")
    PartnerMapping findOne(int id, int customerId);

    /**
     * Select a list of partner mappings from partner_mapping table by customer id
     *
     * @param customerId - id of customer
     * @return Partner mapping list
     */
    List<PartnerMapping> findAllByCustomerId(int customerId);


    /**
     * Insert mapping to partner_mapping
     *
     * @param partnerMapping - mapping id
     * @return Saved partner mapping in database
     */
    @Transactional
    PartnerMapping save(PartnerMapping partnerMapping);


    /**
     * Delete mapping from partner_mapping by mapping id and customer id
     *
     * @param id - mapping id
     * @param customerId - id of customer
     * @return number of modified rows
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM PartnerMapping p WHERE p.id= ?1 AND p.customer.id= ?2")
    int delete(int id, int customerId);
}

