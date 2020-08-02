package com.ec.vivo.web.rest;

import com.ec.vivo.domain.UserEc2Vivo;
import com.ec.vivo.repository.UserEc2VivoRepository;
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
 * REST controller for managing {@link com.ec.vivo.domain.UserEc2Vivo}.
 */
@RestController
@RequestMapping("/api")
public class UserEc2VivoResource {

    private final Logger log = LoggerFactory.getLogger(UserEc2VivoResource.class);

    private static final String ENTITY_NAME = "userEc2Vivo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserEc2VivoRepository userEc2VivoRepository;

    public UserEc2VivoResource(UserEc2VivoRepository userEc2VivoRepository) {
        this.userEc2VivoRepository = userEc2VivoRepository;
    }

    /**
     * {@code POST  /user-ec-2-vivos} : Create a new userEc2Vivo.
     *
     * @param userEc2Vivo the userEc2Vivo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userEc2Vivo, or with status {@code 400 (Bad Request)} if the userEc2Vivo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-ec-2-vivos")
    public Mono<ResponseEntity<UserEc2Vivo>> createUserEc2Vivo(@RequestBody UserEc2Vivo userEc2Vivo) throws URISyntaxException {
        log.debug("REST request to save UserEc2Vivo : {}", userEc2Vivo);
        if (userEc2Vivo.getId() != null) {
            throw new BadRequestAlertException("A new userEc2Vivo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return userEc2VivoRepository.save(userEc2Vivo)            .map(result -> {
                try {
                    return ResponseEntity.created(new URI("/api/user-ec-2-vivos/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /user-ec-2-vivos} : Updates an existing userEc2Vivo.
     *
     * @param userEc2Vivo the userEc2Vivo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userEc2Vivo,
     * or with status {@code 400 (Bad Request)} if the userEc2Vivo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userEc2Vivo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-ec-2-vivos")
    public Mono<ResponseEntity<UserEc2Vivo>> updateUserEc2Vivo(@RequestBody UserEc2Vivo userEc2Vivo) throws URISyntaxException {
        log.debug("REST request to update UserEc2Vivo : {}", userEc2Vivo);
        if (userEc2Vivo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        return userEc2VivoRepository.save(userEc2Vivo)            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
            .map(result -> ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId()))
                .body(result)
            );
    }

    /**
     * {@code GET  /user-ec-2-vivos} : get all the userEc2Vivos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userEc2Vivos in body.
     */
    @GetMapping("/user-ec-2-vivos")
    public Mono<List<UserEc2Vivo>> getAllUserEc2Vivos() {
        log.debug("REST request to get all UserEc2Vivos");
        return userEc2VivoRepository.findAll().collectList();
    }

    /**
     * {@code GET  /user-ec-2-vivos} : get all the userEc2Vivos as a stream.
     * @return the {@link Flux} of userEc2Vivos.
     */
    @GetMapping(value = "/user-ec-2-vivos", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<UserEc2Vivo> getAllUserEc2VivosAsStream() {
        log.debug("REST request to get all UserEc2Vivos as a stream");
        return userEc2VivoRepository.findAll();
    }

    /**
     * {@code GET  /user-ec-2-vivos/:id} : get the "id" userEc2Vivo.
     *
     * @param id the id of the userEc2Vivo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userEc2Vivo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-ec-2-vivos/{id}")
    public Mono<ResponseEntity<UserEc2Vivo>> getUserEc2Vivo(@PathVariable String id) {
        log.debug("REST request to get UserEc2Vivo : {}", id);
        Mono<UserEc2Vivo> userEc2Vivo = userEc2VivoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(userEc2Vivo);
    }

    /**
     * {@code DELETE  /user-ec-2-vivos/:id} : delete the "id" userEc2Vivo.
     *
     * @param id the id of the userEc2Vivo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-ec-2-vivos/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteUserEc2Vivo(@PathVariable String id) {
        log.debug("REST request to delete UserEc2Vivo : {}", id);
        return userEc2VivoRepository.deleteById(id)
            .map(result -> ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build()
        );
    }
}
