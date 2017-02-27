package ru.emitrohin.roox.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;
import ru.emitrohin.roox.model.Customer;
import ru.emitrohin.roox.model.PartnerMapping;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by emitrokhin on 28.02.2017.
 */
public interface PartnerMappingRepository extends Repository<Customer, Integer> {

    PartnerMapping findOne(int id);

    List<PartnerMapping> findAllByCustomerId(int id);

    @Transactional
    PartnerMapping save(PartnerMapping partnerMapping);

    @Transactional
    void delete(int id);
}
