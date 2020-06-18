package com.ec.vivo.web.rest;

import com.ec.vivo.Ec2VivoApp;
import com.ec.vivo.config.TestSecurityConfiguration;
import com.ec.vivo.domain.Region;
import com.ec.vivo.repository.RegionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

/**
 * Integration tests for the {@link RegionResource} REST controller.
 */
@SpringBootTest(classes = { Ec2VivoApp.class, TestSecurityConfiguration.class })
@AutoConfigureWebTestClient
@WithMockUser
public class RegionResourceIT {

    private static final String DEFAULT_REGION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REGION_NAME = "BBBBBBBBBB";

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private WebTestClient webTestClient;

    private Region region;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Region createEntity() {
        Region region = new Region()
            .regionName(DEFAULT_REGION_NAME);
        return region;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Region createUpdatedEntity() {
        Region region = new Region()
            .regionName(UPDATED_REGION_NAME);
        return region;
    }

    @BeforeEach
    public void setupCsrf() {
        webTestClient = webTestClient.mutateWith(csrf());
    }

    @BeforeEach
    public void initTest() {
        regionRepository.deleteAll().block();
        region = createEntity();
    }

    @Test
    public void createRegion() throws Exception {
        int databaseSizeBeforeCreate = regionRepository.findAll().collectList().block().size();
        // Create the Region
        webTestClient.post().uri("/api/regions")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(region))
            .exchange()
            .expectStatus().isCreated();

        // Validate the Region in the database
        List<Region> regionList = regionRepository.findAll().collectList().block();
        assertThat(regionList).hasSize(databaseSizeBeforeCreate + 1);
        Region testRegion = regionList.get(regionList.size() - 1);
        assertThat(testRegion.getRegionName()).isEqualTo(DEFAULT_REGION_NAME);
    }

    @Test
    public void createRegionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = regionRepository.findAll().collectList().block().size();

        // Create the Region with an existing ID
        region.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient.post().uri("/api/regions")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(region))
            .exchange()
            .expectStatus().isBadRequest();

        // Validate the Region in the database
        List<Region> regionList = regionRepository.findAll().collectList().block();
        assertThat(regionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllRegionsAsStream() {
        // Initialize the database
        regionRepository.save(region).block();

        List<Region> regionList = webTestClient.get().uri("/api/regions")
            .accept(MediaType.APPLICATION_STREAM_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_STREAM_JSON)
            .returnResult(Region.class)
            .getResponseBody()
            .filter(region::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(regionList).isNotNull();
        assertThat(regionList).hasSize(1);
        Region testRegion = regionList.get(0);
        assertThat(testRegion.getRegionName()).isEqualTo(DEFAULT_REGION_NAME);
    }

    @Test
    public void getAllRegions() {
        // Initialize the database
        regionRepository.save(region).block();

        // Get all the regionList
        webTestClient.get().uri("/api/regions?sort=id,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id").value(hasItem(region.getId()))
            .jsonPath("$.[*].regionName").value(hasItem(DEFAULT_REGION_NAME));
    }
    
    @Test
    public void getRegion() {
        // Initialize the database
        regionRepository.save(region).block();

        // Get the region
        webTestClient.get().uri("/api/regions/{id}", region.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").value(is(region.getId()))
            .jsonPath("$.regionName").value(is(DEFAULT_REGION_NAME));
    }
    @Test
    public void getNonExistingRegion() {
        // Get the region
        webTestClient.get().uri("/api/regions/{id}", Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isNotFound();
    }

    @Test
    public void updateRegion() throws Exception {
        // Initialize the database
        regionRepository.save(region).block();

        int databaseSizeBeforeUpdate = regionRepository.findAll().collectList().block().size();

        // Update the region
        Region updatedRegion = regionRepository.findById(region.getId()).block();
        updatedRegion
            .regionName(UPDATED_REGION_NAME);

        webTestClient.put().uri("/api/regions")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedRegion))
            .exchange()
            .expectStatus().isOk();

        // Validate the Region in the database
        List<Region> regionList = regionRepository.findAll().collectList().block();
        assertThat(regionList).hasSize(databaseSizeBeforeUpdate);
        Region testRegion = regionList.get(regionList.size() - 1);
        assertThat(testRegion.getRegionName()).isEqualTo(UPDATED_REGION_NAME);
    }

    @Test
    public void updateNonExistingRegion() throws Exception {
        int databaseSizeBeforeUpdate = regionRepository.findAll().collectList().block().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient.put().uri("/api/regions")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(region))
            .exchange()
            .expectStatus().isBadRequest();

        // Validate the Region in the database
        List<Region> regionList = regionRepository.findAll().collectList().block();
        assertThat(regionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteRegion() {
        // Initialize the database
        regionRepository.save(region).block();

        int databaseSizeBeforeDelete = regionRepository.findAll().collectList().block().size();

        // Delete the region
        webTestClient.delete().uri("/api/regions/{id}", region.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isNoContent();

        // Validate the database contains one less item
        List<Region> regionList = regionRepository.findAll().collectList().block();
        assertThat(regionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
