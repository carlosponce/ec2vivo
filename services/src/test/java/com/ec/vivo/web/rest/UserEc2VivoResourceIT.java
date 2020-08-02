package com.ec.vivo.web.rest;

import com.ec.vivo.Ec2VivoApp;
import com.ec.vivo.config.TestSecurityConfiguration;
import com.ec.vivo.domain.UserEc2Vivo;
import com.ec.vivo.repository.UserEc2VivoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

/**
 * Integration tests for the {@link UserEc2VivoResource} REST controller.
 */
@SpringBootTest(classes = { Ec2VivoApp.class, TestSecurityConfiguration.class })
@AutoConfigureWebTestClient
@WithMockUser
public class UserEc2VivoResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_USER_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_USER_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_LOGIN_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN_SOURCE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_REGISTER_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REGISTER_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private UserEc2VivoRepository userEc2VivoRepository;

    @Autowired
    private WebTestClient webTestClient;

    private UserEc2Vivo userEc2Vivo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserEc2Vivo createEntity() {
        UserEc2Vivo userEc2Vivo = new UserEc2Vivo()
            .name(DEFAULT_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .phone(DEFAULT_PHONE)
            .email(DEFAULT_EMAIL)
            .userName(DEFAULT_USER_NAME)
            .userPassword(DEFAULT_USER_PASSWORD)
            .loginSource(DEFAULT_LOGIN_SOURCE)
            .registerDate(DEFAULT_REGISTER_DATE);
        return userEc2Vivo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserEc2Vivo createUpdatedEntity() {
        UserEc2Vivo userEc2Vivo = new UserEc2Vivo()
            .name(UPDATED_NAME)
            .lastName(UPDATED_LAST_NAME)
            .phone(UPDATED_PHONE)
            .email(UPDATED_EMAIL)
            .userName(UPDATED_USER_NAME)
            .userPassword(UPDATED_USER_PASSWORD)
            .loginSource(UPDATED_LOGIN_SOURCE)
            .registerDate(UPDATED_REGISTER_DATE);
        return userEc2Vivo;
    }

    @BeforeEach
    public void setupCsrf() {
        webTestClient = webTestClient.mutateWith(csrf());
    }

    @BeforeEach
    public void initTest() {
        userEc2VivoRepository.deleteAll().block();
        userEc2Vivo = createEntity();
    }

    @Test
    public void createUserEc2Vivo() throws Exception {
        int databaseSizeBeforeCreate = userEc2VivoRepository.findAll().collectList().block().size();
        // Create the UserEc2Vivo
        webTestClient.post().uri("/api/user-ec-2-vivos")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(userEc2Vivo))
            .exchange()
            .expectStatus().isCreated();

        // Validate the UserEc2Vivo in the database
        List<UserEc2Vivo> userEc2VivoList = userEc2VivoRepository.findAll().collectList().block();
        assertThat(userEc2VivoList).hasSize(databaseSizeBeforeCreate + 1);
        UserEc2Vivo testUserEc2Vivo = userEc2VivoList.get(userEc2VivoList.size() - 1);
        assertThat(testUserEc2Vivo.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testUserEc2Vivo.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testUserEc2Vivo.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testUserEc2Vivo.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testUserEc2Vivo.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testUserEc2Vivo.getUserPassword()).isEqualTo(DEFAULT_USER_PASSWORD);
        assertThat(testUserEc2Vivo.getLoginSource()).isEqualTo(DEFAULT_LOGIN_SOURCE);
        assertThat(testUserEc2Vivo.getRegisterDate()).isEqualTo(DEFAULT_REGISTER_DATE);
    }

    @Test
    public void createUserEc2VivoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userEc2VivoRepository.findAll().collectList().block().size();

        // Create the UserEc2Vivo with an existing ID
        userEc2Vivo.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient.post().uri("/api/user-ec-2-vivos")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(userEc2Vivo))
            .exchange()
            .expectStatus().isBadRequest();

        // Validate the UserEc2Vivo in the database
        List<UserEc2Vivo> userEc2VivoList = userEc2VivoRepository.findAll().collectList().block();
        assertThat(userEc2VivoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllUserEc2VivosAsStream() {
        // Initialize the database
        userEc2VivoRepository.save(userEc2Vivo).block();

        List<UserEc2Vivo> userEc2VivoList = webTestClient.get().uri("/api/user-ec-2-vivos")
            .accept(MediaType.APPLICATION_STREAM_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_STREAM_JSON)
            .returnResult(UserEc2Vivo.class)
            .getResponseBody()
            .filter(userEc2Vivo::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(userEc2VivoList).isNotNull();
        assertThat(userEc2VivoList).hasSize(1);
        UserEc2Vivo testUserEc2Vivo = userEc2VivoList.get(0);
        assertThat(testUserEc2Vivo.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testUserEc2Vivo.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testUserEc2Vivo.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testUserEc2Vivo.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testUserEc2Vivo.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testUserEc2Vivo.getUserPassword()).isEqualTo(DEFAULT_USER_PASSWORD);
        assertThat(testUserEc2Vivo.getLoginSource()).isEqualTo(DEFAULT_LOGIN_SOURCE);
        assertThat(testUserEc2Vivo.getRegisterDate()).isEqualTo(DEFAULT_REGISTER_DATE);
    }

    @Test
    public void getAllUserEc2Vivos() {
        // Initialize the database
        userEc2VivoRepository.save(userEc2Vivo).block();

        // Get all the userEc2VivoList
        webTestClient.get().uri("/api/user-ec-2-vivos?sort=id,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id").value(hasItem(userEc2Vivo.getId()))
            .jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME))
            .jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME))
            .jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE))
            .jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL))
            .jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME))
            .jsonPath("$.[*].userPassword").value(hasItem(DEFAULT_USER_PASSWORD))
            .jsonPath("$.[*].loginSource").value(hasItem(DEFAULT_LOGIN_SOURCE))
            .jsonPath("$.[*].registerDate").value(hasItem(DEFAULT_REGISTER_DATE.toString()));
    }
    
    @Test
    public void getUserEc2Vivo() {
        // Initialize the database
        userEc2VivoRepository.save(userEc2Vivo).block();

        // Get the userEc2Vivo
        webTestClient.get().uri("/api/user-ec-2-vivos/{id}", userEc2Vivo.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").value(is(userEc2Vivo.getId()))
            .jsonPath("$.name").value(is(DEFAULT_NAME))
            .jsonPath("$.lastName").value(is(DEFAULT_LAST_NAME))
            .jsonPath("$.phone").value(is(DEFAULT_PHONE))
            .jsonPath("$.email").value(is(DEFAULT_EMAIL))
            .jsonPath("$.userName").value(is(DEFAULT_USER_NAME))
            .jsonPath("$.userPassword").value(is(DEFAULT_USER_PASSWORD))
            .jsonPath("$.loginSource").value(is(DEFAULT_LOGIN_SOURCE))
            .jsonPath("$.registerDate").value(is(DEFAULT_REGISTER_DATE.toString()));
    }
    @Test
    public void getNonExistingUserEc2Vivo() {
        // Get the userEc2Vivo
        webTestClient.get().uri("/api/user-ec-2-vivos/{id}", Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isNotFound();
    }

    @Test
    public void updateUserEc2Vivo() throws Exception {
        // Initialize the database
        userEc2VivoRepository.save(userEc2Vivo).block();

        int databaseSizeBeforeUpdate = userEc2VivoRepository.findAll().collectList().block().size();

        // Update the userEc2Vivo
        UserEc2Vivo updatedUserEc2Vivo = userEc2VivoRepository.findById(userEc2Vivo.getId()).block();
        updatedUserEc2Vivo
            .name(UPDATED_NAME)
            .lastName(UPDATED_LAST_NAME)
            .phone(UPDATED_PHONE)
            .email(UPDATED_EMAIL)
            .userName(UPDATED_USER_NAME)
            .userPassword(UPDATED_USER_PASSWORD)
            .loginSource(UPDATED_LOGIN_SOURCE)
            .registerDate(UPDATED_REGISTER_DATE);

        webTestClient.put().uri("/api/user-ec-2-vivos")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedUserEc2Vivo))
            .exchange()
            .expectStatus().isOk();

        // Validate the UserEc2Vivo in the database
        List<UserEc2Vivo> userEc2VivoList = userEc2VivoRepository.findAll().collectList().block();
        assertThat(userEc2VivoList).hasSize(databaseSizeBeforeUpdate);
        UserEc2Vivo testUserEc2Vivo = userEc2VivoList.get(userEc2VivoList.size() - 1);
        assertThat(testUserEc2Vivo.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUserEc2Vivo.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testUserEc2Vivo.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testUserEc2Vivo.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUserEc2Vivo.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testUserEc2Vivo.getUserPassword()).isEqualTo(UPDATED_USER_PASSWORD);
        assertThat(testUserEc2Vivo.getLoginSource()).isEqualTo(UPDATED_LOGIN_SOURCE);
        assertThat(testUserEc2Vivo.getRegisterDate()).isEqualTo(UPDATED_REGISTER_DATE);
    }

    @Test
    public void updateNonExistingUserEc2Vivo() throws Exception {
        int databaseSizeBeforeUpdate = userEc2VivoRepository.findAll().collectList().block().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient.put().uri("/api/user-ec-2-vivos")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(userEc2Vivo))
            .exchange()
            .expectStatus().isBadRequest();

        // Validate the UserEc2Vivo in the database
        List<UserEc2Vivo> userEc2VivoList = userEc2VivoRepository.findAll().collectList().block();
        assertThat(userEc2VivoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteUserEc2Vivo() {
        // Initialize the database
        userEc2VivoRepository.save(userEc2Vivo).block();

        int databaseSizeBeforeDelete = userEc2VivoRepository.findAll().collectList().block().size();

        // Delete the userEc2Vivo
        webTestClient.delete().uri("/api/user-ec-2-vivos/{id}", userEc2Vivo.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isNoContent();

        // Validate the database contains one less item
        List<UserEc2Vivo> userEc2VivoList = userEc2VivoRepository.findAll().collectList().block();
        assertThat(userEc2VivoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
