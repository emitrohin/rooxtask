package ru.emitrohin.roox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.emitrohin.roox.model.PartnerMapping;
import ru.emitrohin.roox.repository.CustomerRepository;
import ru.emitrohin.roox.repository.PartnerMappingRepository;
import ru.emitrohin.roox.util.exception.ExceptionUtil;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   28.02.2017.
 */

@Service
public class PartnerMappingService {

    private PartnerMappingRepository partnerMappingRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public PartnerMappingService(PartnerMappingRepository partnerMappingRepository, CustomerRepository customerRepository) {
        this.partnerMappingRepository = partnerMappingRepository;
        this.customerRepository = customerRepository;
    }

    public PartnerMapping get(int id, int customerId){
        return ExceptionUtil.checkNotFoundWithId(partnerMappingRepository.findOne(id, customerId), id);
    }

    public List<PartnerMapping> findAllByCustomerId(int customerId){
        return partnerMappingRepository.findAllByCustomerId(customerId);
    }

    public PartnerMapping update(PartnerMapping partnerMapping, int customerId) {
        Assert.notNull(partnerMapping, "PartnerMapping must not be null");
        return ExceptionUtil.checkNotFoundWithId(save(partnerMapping, customerId), partnerMapping.getId());
    }

    public PartnerMapping create(PartnerMapping partnerMapping, int customerId) {
        Assert.notNull(partnerMapping, "PartnerMapping must not be null");
        return save(partnerMapping, customerId);
    }

    private PartnerMapping save(PartnerMapping partnerMapping, int customerId){
        if (!partnerMapping.isNew() && get(partnerMapping.getId(), customerId) == null) {
            return null;
        }
        partnerMapping.setCustomer(customerRepository.findOne(customerId));
        return partnerMappingRepository.save(partnerMapping);
    }

    public void delete(int id, int customerId){
        ExceptionUtil.checkNotFoundWithAuthorization(partnerMappingRepository.findOne(id, customerId), customerId, "PartnerMapping with id " + id);
        partnerMappingRepository.delete(id, customerId);
    }
}
