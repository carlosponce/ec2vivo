package com.ec.vivo.web.rest;

import com.ec.vivo.Ec2VivoApp;
import com.ec.vivo.config.TestSecurityConfiguration;
import com.ec.vivo.domain.Location;
import com.ec.vivo.repository.LocationRepository;

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
 * Integration tests for the {@link LocationResource} REST controller.
 */
@SpringBootTest(classes = { Ec2VivoApp.class, TestSecurityConfiguration.class })
@AutoConfigureWebTestClient
@WithMockUser
public class LocationResourceIT {

    private static final String DEFAULT_STREET_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_STREET_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_STATE_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_STATE_PROVINCE = "BBBBBBBBBB";

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private WebTestClient webTestClient;

    private Location location;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Location createEntity() {
        Location location = new Location()
            .streetAddress(DEFAULT_STREET_ADDRESS)
            .postalCode(DEFAULT_POSTAL_CODE)
            .city(DEFAULT_CITY)
            .stateProvince(DEFAULT_STATE_PROVINCE);
        return location;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Location createUpdatedEntity() {
        Location location = new Location()
            .streetAddress(UPDATED_STREET_ADDRESS)
            .postalCode(UPDATED_POSTAL_CODE)
            .city(UPDATED_CITY)
            .stateProvince(UPDATED_STATE_PROVINCE);
        return location;
    }

    @BeforeEach
    public void setupCsrf() {
        webTestClient = webTestClient.mutateWith(csrf());
    }

    @BeforeEach
    public void initTest() {
        locationRepository.deleteAll().block();
        location = createEntity();
    }

    @Test
    public void createLocation() throws Exception {
        int databaseSizeBeforeCreate = locationRepository.findAll().collectList().block().size();
        // Create the Location
        webTestClient.post().uri("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(location))
            .exchange()
            .expectStatus().isCreated();

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll().collectList().block();
        assertThat(locationList).hasSize(databaseSizeBeforeCreate + 1);
        Location testLocation = locationList.get(locationList.size() - 1);
        assertThat(testLocation.getStreetAddress()).isEqualTo(DEFAULT_STREET_ADDRESS);
        assertThat(testLocation.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testLocation.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testLocation.getStateProvince()).isEqualTo(DEFAULT_STATE_PROVINCE);
    }

    @Test
    public void createLocationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = locationRepository.findAll().collectList().block().size();

        // Create the Location with an existing ID
        location.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient.post().uri("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(location))
            .exchange()
            .expectStatus().isBadRequest();

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll().collectList().block();
        assertThat(locationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllLocationsAsStream() {
        // Initialize the database
        locationRepository.save(location).block();

        List<Location> locationList = webTestClient.get().uri("/api/locations")
            .accept(MediaType.APPLICATION_STREAM_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_STREAM_JSON)
            .returnResult(Location.class)
            .getResponseBody()
            .filter(location::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(locationList).isNotNull();
        assertThat(locationList).hasSize(1);
        Location testLocation = locationList.get(0);
        assertThat(testLocation.getStreetAddress()).isEqualTo(DEFAULT_STREET_ADDRESS);
        assertThat(testLocation.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testLocation.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testLocation.getStateProvince()).isEqualTo(DEFAULT_STATE_PROVINCE);
    }

    @Test
    public void getAllLocations() {
        // Initialize the database
        locationRepository.save(location).block();

        // Get all the locationList
        webTestClient.get().uri("/api/locations?sort=id,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id").value(hasItem(location.getId()))
            .jsonPath("$.[*].streetAddress").value(hasItem(DEFAULT_STREET_ADDRESS))
            .jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE))
            .jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY))
            .jsonPath("$.[*].stateProvince").value(hasItem(DEFAULT_STATE_PROVINCE));
    }
    
    @Test
    public void getLocation() {
        // Initialize the database
        locationRepository.save(location).block();

        // Get the location
        webTestClient.get().uri("/api/locations/{id}", location.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").value(is(location.getId()))
            .jsonPath("$.streetAddress").value(is(DEFAULT_STREET_ADDRESS))
            .jsonPath("$.postalCode").value(is(DEFAULT_POSTAL_CODE))
            .jsonPath("$.city").value(is(DEFAULT_CITY))
            .jsonPath("$.stateProvince").value(is(DEFAULT_STATE_PROVINCE));
    }
    @Test
    public void getNonExistingLocation() {
        // Get the location
        webTestClient.get().uri("/api/locations/{id}", Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isNotFound();
    }

    @Test
    public void updateLocation() throws Exception {
        // Initialize the database
        locationRepository.save(location).block();

        int databaseSizeBeforeUpdate = locationRepository.findAll().collectList().block().size();

        // Update the location
        Location updatedLocation = locationRepository.findById(location.getId()).block();
        updatedLocation
            .streetAddress(UPDATED_STREET_ADDRESS)
            .postalCode(UPDATED_POSTAL_CODE)
            .city(UPDATED_CITY)
            .stateProvince(UPDATED_STATE_PROVINCE);

        webTestClient.put().uri("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedLocation))
            .exchange()
            .expectStatus().isOk();

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll().collectList().block();
        assertThat(locationList).hasSize(databaseSizeBeforeUpdate);
        Location testLocation = locationList.get(locationList.size() - 1);
        assertThat(testLocation.getStreetAddress()).isEqualTo(UPDATED_STREET_ADDRESS);
        assertThat(testLocation.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testLocation.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testLocation.getStateProvince()).isEqualTo(UPDATED_STATE_PROVINCE);
    }

    @Test
    public void updateNonExistingLocation() throws Exception {
        int databaseSizeBeforeUpdate = locationRepository.findAll().collectList().block().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient.put().uri("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(location))
            .exchange()
            .expectStatus().isBadRequest();

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll().collectList().block();
        assertThat(locationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteLocation() {
        // Initialize the database
        locationRepository.save(location).block();

        int databaseSizeBeforeDelete = locationRepository.findAll().collectList().block().size();

        // Delete the location
        webTestClient.delete().uri("/api/locations/{id}", location.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isNoContent();

        // Validate the database contains one less item
        List<Location> locationList = locationRepository.findAll().collectList().block();
        assertThat(locationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
