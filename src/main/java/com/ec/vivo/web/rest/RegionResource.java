package com.ec.vivo.web.rest;

import com.ec.vivo.domain.Region;
import com.ec.vivo.repository.RegionRepository;
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
 * REST controller for managing {@link com.ec.vivo.domain.Region}.
 */
@RestController
@RequestMapping("/api")
public class RegionResource {

    private final Logger log = LoggerFactory.getLogger(RegionResource.class);

    private static final String ENTITY_NAME = "region";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RegionRepository regionRepository;

    public RegionResource(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    /**
     * {@code POST  /regions} : Create a new region.
     *
     * @param region the region to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new region, or with status {@code 400 (Bad Request)} if the region has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/regions")
    public Mono<ResponseEntity<Region>> createRegion(@RequestBody Region region) throws URISyntaxException {
        log.debug("REST request to save Region : {}", region);
        if (region.getId() != null) {
            throw new BadRequestAlertException("A new region cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return regionRepository.save(region)            .map(result -> {
                try {
                    return ResponseEntity.created(new URI("/api/regions/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /regions} : Updates an existing region.
     *
     * @param region the region to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated region,
     * or with status {@code 400 (Bad Request)} if the region is not valid,
     * or with status {@code 500 (Internal Server Error)} if the region couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/regions")
    public Mono<ResponseEntity<Region>> updateRegion(@RequestBody Region region) throws URISyntaxException {
        log.debug("REST request to update Region : {}", region);
        if (region.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        return regionRepository.save(region)            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
            .map(result -> ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId()))
                .body(result)
            );
    }

    /**
     * {@code GET  /regions} : get all the regions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of regions in body.
     */
    @GetMapping("/regions")
    public Mono<List<Region>> getAllRegions() {
        log.debug("REST request to get all Regions");
        return regionRepository.findAll().collectList();
    }

    /**
     * {@code GET  /regions} : get all the regions as a stream.
     * @return the {@link Flux} of regions.
     */
    @GetMapping(value = "/regions", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Region> getAllRegionsAsStream() {
        log.debug("REST request to get all Regions as a stream");
        return regionRepository.findAll();
    }

    /**
     * {@code GET  /regions/:id} : get the "id" region.
     *
     * @param id the id of the region to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the region, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/regions/{id}")
    public Mono<ResponseEntity<Region>> getRegion(@PathVariable String id) {
        log.debug("REST request to get Region : {}", id);
        Mono<Region> region = regionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(region);
    }

    /**
     * {@code DELETE  /regions/:id} : delete the "id" region.
     *
     * @param id the id of the region to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/regions/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteRegion(@PathVariable String id) {
        log.debug("REST request to delete Region : {}", id);
        return regionRepository.deleteById(id)
            .map(result -> ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build()
        );
    }
}
