package ru.emitrohin.roox.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.emitrohin.roox.model.PartnerMapping;
import ru.emitrohin.roox.service.PartnerMappingService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   28.02.2017.
 */

@RestController
@RequestMapping(value = CustomerRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class PartnerMappingRestController extends BaseRestController {

    private PartnerMappingService partnerMappingService;

    @Autowired
    public PartnerMappingRestController(PartnerMappingService partnerMappingService) {
        this.partnerMappingService = partnerMappingService;
    }

    @GetMapping(value = "/{customerId}/partner-mappings")
    public List<PartnerMapping> getAll(@PathVariable("customerId") int customerId) {
        checkAuthorizedId(customerId);
        LOG.info("Get all partner mapping by customer {}", customerId);
        return partnerMappingService.findAllByCustomerId(customerId);
    }

    @GetMapping("/@me/partner-mappings")
    public List<PartnerMapping> getAll() {
        int authorizedId = 100001; //AuthorizedCustomer.id();
        LOG.info("Get all partner mapping by @me id {}", authorizedId);
        return partnerMappingService.findAllByCustomerId(authorizedId);
    }

    @GetMapping(value = "/{customerId}/partner-mappings/{mappingId}")
    public PartnerMapping get(@PathVariable("customerId") int customerId, @PathVariable("mappingId") int mappingId) {
        checkAuthorizedId(customerId);
        LOG.info("Get partner mapping {} by customer {}", mappingId, customerId);
        return partnerMappingService.get(mappingId, customerId);
    }

    @GetMapping("/@me/partner-mappings/{mappingId}")
    public PartnerMapping get(@PathVariable("mappingId") int mappingId) {
        int authorizedId = 100001; //AuthorizedCustomer.id();
        LOG.info("Get partner mapping {} by @me id {}", mappingId, authorizedId);
        return partnerMappingService.get(mappingId, authorizedId);
    }

    @PostMapping(value = "/{customerId}/partner-mappings", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PartnerMapping> create(@Valid @RequestBody PartnerMapping partnerMapping, @PathVariable("customerId") int customerId) {
        checkAuthorizedId(customerId);
        LOG.info("Create partner mapping {} for Customer {}", partnerMapping, customerId);
        return createResponseEntity(partnerMapping, customerId);
    }

    @PostMapping(value = "/@me/partner-mappings", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PartnerMapping> create(@Valid @RequestBody PartnerMapping partnerMapping) {
        int authorizedId = 100001; //AuthorizedCustomer.id();
        LOG.info("Create partner mapping  {} by @me id {}", partnerMapping, authorizedId);
        return createResponseEntity(partnerMapping, authorizedId);
    }

    private ResponseEntity<PartnerMapping> createResponseEntity(PartnerMapping partnerMapping, int customerId) {
        partnerMapping.setId(null);
        PartnerMapping created = partnerMappingService.create(partnerMapping, customerId);

        URI uriOfNewResource = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }


    @PutMapping(value = "/{customerId}/partner-mappings", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody PartnerMapping partnerMapping, @PathVariable("customerId") int customerId) {
        checkAuthorizedId(customerId);
        LOG.info("Update partner mapping {} for Customer {}", partnerMapping, customerId);
        partnerMappingService.update(partnerMapping, customerId);
    }

    @PutMapping(value = "/@me/partner-mappings", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody PartnerMapping partnerMapping) {
        int authorizedId = 100001; //AuthorizedCustomer.id();
        LOG.info("Update partner mapping {}  by @me id {}", partnerMapping, authorizedId);
        partnerMappingService.update(partnerMapping, authorizedId);
    }

    @DeleteMapping("/{customerId}/partner-mappings/{mappingId}")
    public void delete(@PathVariable("customerId") int customerId, @PathVariable("mappingId") int mappingId) {
        checkAuthorizedId(customerId);
        LOG.info("Delete partner mapping {} for Customer {}", mappingId, customerId);
        partnerMappingService.delete(mappingId, customerId);
    }

    @DeleteMapping("/@me/partner-mappings/{mappingId}")
    public void delete(@PathVariable("mappingId") int mappingId) {
        int authorizedId = 100001; //AuthorizedCustomer.id();
        LOG.info("Delete partner mapping {} by @me id {}", mappingId, authorizedId);
        partnerMappingService.delete(mappingId, authorizedId);
    }
}
