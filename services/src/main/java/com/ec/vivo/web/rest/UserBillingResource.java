package com.ec.vivo.web.rest;

import com.ec.vivo.domain.UserBilling;
import com.ec.vivo.repository.UserBillingRepository;
import com.ec.vivo.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.reactive.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ec.vivo.domain.UserBilling}.
 */
@RestController
@RequestMapping("/api")
public class UserBillingResource {

    private final Logger log = LoggerFactory.getLogger(UserBillingResource.class);

    private static final String ENTITY_NAME = "userBilling";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserBillingRepository userBillingRepository;

    public UserBillingResource(UserBillingRepository userBillingRepository) {
        this.userBillingRepository = userBillingRepository;
    }

    /**
     * {@code POST  /user-billings} : Create a new userBilling.
     *
     * @param userBilling the userBilling to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userBilling, or with status {@code 400 (Bad Request)} if the userBilling has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-billings")
    public Mono<ResponseEntity<UserBilling>> createUserBilling(@RequestBody UserBilling userBilling) throws URISyntaxException {
        log.debug("REST request to save UserBilling : {}", userBilling);
        if (userBilling.getId() != null) {
            throw new BadRequestAlertException("A new userBilling cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return userBillingRepository.save(userBilling)            .map(result -> {
                try {
                    return ResponseEntity.created(new URI("/api/user-billings/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /user-billings} : Updates an existing userBilling.
     *
     * @param userBilling the userBilling to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userBilling,
     * or with status {@code 400 (Bad Request)} if the userBilling is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userBilling couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-billings")
    public Mono<ResponseEntity<UserBilling>> updateUserBilling(@RequestBody UserBilling userBilling) throws URISyntaxException {
        log.debug("REST request to update UserBilling : {}", userBilling);
        if (userBilling.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        return userBillingRepository.save(userBilling)            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
            .map(result -> ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId()))
                .body(result)
            );
    }

    /**
     * {@code GET  /user-billings} : get all the userBillings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userBillings in body.
     */
    @GetMapping("/user-billings")
    public Mono<List<UserBilling>> getAllUserBillings() {
        log.debug("REST request to get all UserBillings");
        return userBillingRepository.findAll().collectList();
    }

    /**
     * {@code GET  /user-billings} : get all the userBillings as a stream.
     * @return the {@link Flux} of userBillings.
     */
    @GetMapping(value = "/user-billings", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<UserBilling> getAllUserBillingsAsStream() {
        log.debug("REST request to get all UserBillings as a stream");
        return userBillingRepository.findAll();
    }

    /**
     * {@code GET  /user-billings/:id} : get the "id" userBilling.
     *
     * @param id the id of the userBilling to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userBilling, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-billings/{id}")
    public Mono<ResponseEntity<UserBilling>> getUserBilling(@PathVariable String id) {
        log.debug("REST request to get UserBilling : {}", id);
        Mono<UserBilling> userBilling = userBillingRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(userBilling);
    }

    /**
     * {@code DELETE  /user-billings/:id} : delete the "id" userBilling.
     *
     * @param id the id of the userBilling to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-billings/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteUserBilling(@PathVariable String id) {
        log.debug("REST request to delete UserBilling : {}", id);
        return userBillingRepository.deleteById(id)
            .map(result -> ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build()
        );
    }
}
