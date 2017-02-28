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
        return ExceptionUtil.checkNotFoundWithAuthorization(partnerMappingRepository.findOne(id), customerId, "PartnerMapping with id " + id);
    }

    public List<PartnerMapping> findAllByCustomerId(int customerId){
        return partnerMappingRepository.findAllByCustomerId(customerId);
    }

    public PartnerMapping save(PartnerMapping partnerMapping, int customerId){
        Assert.notNull(partnerMapping, "PartnerMapping must not be null");
        if (!partnerMapping.isNew() && get(partnerMapping.getId(), customerId) == null) {
            return null;
        }
        partnerMapping.setCustomer(customerRepository.findOne(customerId));
        return partnerMappingRepository.save(partnerMapping);
    }

    public boolean delete(int id, int customerId){
        ExceptionUtil.checkNotFoundWithAuthorization(customerRepository.findOne(id), customerId, "PartnerMapping with id " + id);
        return partnerMappingRepository.delete(id) != 0;
    }
}
