package ru.emitrohin.roox.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;
import ru.emitrohin.roox.model.PartnerMapping;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by emitrokhin on 28.02.2017.
 */
public interface PartnerMappingRepository extends Repository<PartnerMapping, Integer> {

    PartnerMapping findOne(int id);

    List<PartnerMapping> findAllByCustomerId(int customerId);

    @Transactional
    PartnerMapping save(PartnerMapping partnerMapping);

    @Transactional
    @Modifying
    void delete(int id);
}

