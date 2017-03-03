package ru.emitrohin.roox.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.emitrohin.roox.model.PartnerMapping;
import ru.emitrohin.roox.security.AuthorizedCustomer;
import ru.emitrohin.roox.service.PartnerMappingService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Rest API for customer
 *
 * @author Evgeniy Mitrokhin
 */
@RestController
@RequestMapping(value = CustomerRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class PartnerMappingRestController extends BaseRestController {

    private PartnerMappingService partnerMappingService;

    @Autowired
    public PartnerMappingRestController(PartnerMappingService partnerMappingService) {
        this.partnerMappingService = partnerMappingService;
    }

    /**
     * GET method.
     * Returns List of PartnerMapping by customerId is json
     *
     * @param customerId - id of customer
     * @return list of PartnerMapping from database
     */
    @GetMapping(value = "/{customerId}/partner-mappings")
    public List<PartnerMapping> getAll(@PathVariable("customerId") int customerId) {
        checkAuthorizedId(customerId);
        LOG.info("Get all partner mapping by customer {}", customerId);
        return partnerMappingService.findAllByCustomerId(customerId);
    }

    /**
     * GET method.
     * Returns List of PartnerMapping by customerId is json
     * Uses @me literal. Id of customer is taken from AuthorizedCustomer.
     *
     * @return list of PartnerMapping from database
     */
    @GetMapping("/@me/partner-mappings")
    public List<PartnerMapping> getAll() {
        int authorizedId = AuthorizedCustomer.id();
        LOG.info("Get all partner mapping by @me id {}", authorizedId);
        return partnerMappingService.findAllByCustomerId(authorizedId);
    }

    /**
     * GET method.
     * Returns PartnerMapping by customerId is json
     *
     * @param customerId - id of customer
     * @return PartnerMapping from database
     */
    @GetMapping(value = "/{customerId}/partner-mappings/{mappingId}")
    public PartnerMapping get(@PathVariable("customerId") int customerId, @PathVariable("mappingId") int mappingId) {
        checkAuthorizedId(customerId);
        LOG.info("Get partner mapping {} by customer {}", mappingId, customerId);
        return partnerMappingService.get(mappingId, customerId);
    }

    /**
     * GET method.
     * Returns PartnerMapping by customerId is json
     * Uses @me literal. Id of customer is taken from AuthorizedCustomer.
     *
     * @return PartnerMapping from database
     */
    @GetMapping("/@me/partner-mappings/{mappingId}")
    public PartnerMapping get(@PathVariable("mappingId") int mappingId) {
        int authorizedId = AuthorizedCustomer.id();
        LOG.info("Get partner mapping {} by @me id {}", mappingId, authorizedId);
        return partnerMappingService.get(mappingId, authorizedId);
    }

    /**
     * POST method.
     * Creates new PartnerMapping by customerId and partnerMapping that comes with request body in json
     *
     * @param customerId     - id of customer
     * @param partnerMapping - partnerMapping from request
     * @return ResponseEntity for partnerMapping
     */
    @PostMapping(value = "/{customerId}/partner-mappings", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PartnerMapping> create(@Valid @RequestBody PartnerMapping partnerMapping, @PathVariable("customerId") int customerId) {
        checkAuthorizedId(customerId);
        LOG.info("Create partner mapping {} for Customer {}", partnerMapping, customerId);
        return createResponseEntity(partnerMapping, customerId);
    }

    /**
     * POST method.
     * Creates new PartnerMapping by customerId and partnerMapping that comes with request body in json
     * Uses @me literal. Id of customer is taken from AuthorizedCustomer.
     *
     * @param partnerMapping - partnerMapping from request
     * @return ResponseEntity for partnerMapping
     */
    @PostMapping(value = "/@me/partner-mappings", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PartnerMapping> create(@Valid @RequestBody PartnerMapping partnerMapping) {
        int authorizedId = AuthorizedCustomer.id();
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

    /**
     * PUT method.
     * Updates existing PartnerMapping by customerId, partnerMapping that comes with request body in json, and id of existing mapping
     *
     * @param partnerMapping - partnerMapping from request
     * @param customerId - id of customer
     * @param mappingId - id of PartnerMapping
     */
    @PutMapping(value = "/{customerId}/partner-mappings/{mappingId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody PartnerMapping partnerMapping, @PathVariable("customerId") int customerId, @PathVariable("mappingId") int mappingId) {
        checkAuthorizedId(customerId);
        partnerMapping.setId(mappingId);
        LOG.info("Update partner mapping {} for Customer {}", partnerMapping, customerId);
        partnerMappingService.update(partnerMapping, customerId);
    }

    /**
     * PUT method.
     * Updates existing PartnerMapping by customerId, partnerMapping that comes with request body in json, and id of existing mapping
     * Uses @me literal. Id of customer is taken from AuthorizedCustomer.
     *
     * @param partnerMapping - partnerMapping from request
     * @param mappingId - id of PartnerMapping
     */
    @PutMapping(value = "/@me/partner-mappings/{mappingId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody PartnerMapping partnerMapping, @PathVariable("mappingId") int mappingId) {
        int authorizedId = AuthorizedCustomer.id();
        partnerMapping.setId(mappingId);
        LOG.info("Update partner mapping {} by @me id {}", partnerMapping, authorizedId);
        partnerMappingService.update(partnerMapping, authorizedId);
    }

    /**
     * DELETE method.
     * Deletes existing PartnerMapping by customerId and id of existing mapping
     *
     * @param customerId - id of customer
     * @param mappingId - id of PartnerMapping
     */
    @DeleteMapping("/{customerId}/partner-mappings/{mappingId}")
    public void delete(@PathVariable("customerId") int customerId, @PathVariable("mappingId") int mappingId) {
        checkAuthorizedId(customerId);
        LOG.info("Delete partner mapping {} for Customer {}", mappingId, customerId);
        partnerMappingService.delete(mappingId, customerId);
    }

    /**
     * DELETE method.
     * Deletes existing PartnerMapping by customerId and id of existing mapping
     * Uses @me literal. Id of customer is taken from AuthorizedCustomer.
     *
     * @param mappingId - id of PartnerMapping
     */
    @DeleteMapping("/@me/partner-mappings/{mappingId}")
    public void delete(@PathVariable("mappingId") int mappingId) {
        int authorizedId = AuthorizedCustomer.id();
        LOG.info("Delete partner mapping {} by @me id {}", mappingId, authorizedId);
        partnerMappingService.delete(mappingId, authorizedId);
    }
}
