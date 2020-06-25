package com.ec.vivo.web.rest;

import com.ec.vivo.Ec2VivoApp;
import com.ec.vivo.config.TestSecurityConfiguration;
import com.ec.vivo.domain.UserBilling;
import com.ec.vivo.repository.UserBillingRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

/**
 * Integration tests for the {@link UserBillingResource} REST controller.
 */
@SpringBootTest(classes = { Ec2VivoApp.class, TestSecurityConfiguration.class })
@AutoConfigureWebTestClient
@WithMockUser
public class UserBillingResourceIT {

    private static final String DEFAULT_EVENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_EVENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TICKET_ID = "AAAAAAAAAA";
    private static final String UPDATED_TICKET_ID = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BILLING_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BILLING_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_DISCOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DISCOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(2);

    @Autowired
    private UserBillingRepository userBillingRepository;

    @Autowired
    private WebTestClient webTestClient;

    private UserBilling userBilling;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserBilling createEntity() {
        UserBilling userBilling = new UserBilling()
            .eventId(DEFAULT_EVENT_ID)
            .ticketId(DEFAULT_TICKET_ID)
            .billingDate(DEFAULT_BILLING_DATE)
            .email(DEFAULT_EMAIL)
            .discount(DEFAULT_DISCOUNT)
            .price(DEFAULT_PRICE);
        return userBilling;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserBilling createUpdatedEntity() {
        UserBilling userBilling = new UserBilling()
            .eventId(UPDATED_EVENT_ID)
            .ticketId(UPDATED_TICKET_ID)
            .billingDate(UPDATED_BILLING_DATE)
            .email(UPDATED_EMAIL)
            .discount(UPDATED_DISCOUNT)
            .price(UPDATED_PRICE);
        return userBilling;
    }

    @BeforeEach
    public void setupCsrf() {
        webTestClient = webTestClient.mutateWith(csrf());
    }

    @BeforeEach
    public void initTest() {
        userBillingRepository.deleteAll().block();
        userBilling = createEntity();
    }

    @Test
    public void createUserBilling() throws Exception {
        int databaseSizeBeforeCreate = userBillingRepository.findAll().collectList().block().size();
        // Create the UserBilling
        webTestClient.post().uri("/api/user-billings")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(userBilling))
            .exchange()
            .expectStatus().isCreated();

        // Validate the UserBilling in the database
        List<UserBilling> userBillingList = userBillingRepository.findAll().collectList().block();
        assertThat(userBillingList).hasSize(databaseSizeBeforeCreate + 1);
        UserBilling testUserBilling = userBillingList.get(userBillingList.size() - 1);
        assertThat(testUserBilling.getEventId()).isEqualTo(DEFAULT_EVENT_ID);
        assertThat(testUserBilling.getTicketId()).isEqualTo(DEFAULT_TICKET_ID);
        assertThat(testUserBilling.getBillingDate()).isEqualTo(DEFAULT_BILLING_DATE);
        assertThat(testUserBilling.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testUserBilling.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testUserBilling.getPrice()).isEqualTo(DEFAULT_PRICE);
    }

    @Test
    public void createUserBillingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userBillingRepository.findAll().collectList().block().size();

        // Create the UserBilling with an existing ID
        userBilling.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient.post().uri("/api/user-billings")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(userBilling))
            .exchange()
            .expectStatus().isBadRequest();

        // Validate the UserBilling in the database
        List<UserBilling> userBillingList = userBillingRepository.findAll().collectList().block();
        assertThat(userBillingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllUserBillingsAsStream() {
        // Initialize the database
        userBillingRepository.save(userBilling).block();

        List<UserBilling> userBillingList = webTestClient.get().uri("/api/user-billings")
            .accept(MediaType.APPLICATION_STREAM_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_STREAM_JSON)
            .returnResult(UserBilling.class)
            .getResponseBody()
            .filter(userBilling::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(userBillingList).isNotNull();
        assertThat(userBillingList).hasSize(1);
        UserBilling testUserBilling = userBillingList.get(0);
        assertThat(testUserBilling.getEventId()).isEqualTo(DEFAULT_EVENT_ID);
        assertThat(testUserBilling.getTicketId()).isEqualTo(DEFAULT_TICKET_ID);
        assertThat(testUserBilling.getBillingDate()).isEqualTo(DEFAULT_BILLING_DATE);
        assertThat(testUserBilling.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testUserBilling.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testUserBilling.getPrice()).isEqualTo(DEFAULT_PRICE);
    }

    @Test
    public void getAllUserBillings() {
        // Initialize the database
        userBillingRepository.save(userBilling).block();

        // Get all the userBillingList
        webTestClient.get().uri("/api/user-billings?sort=id,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id").value(hasItem(userBilling.getId()))
            .jsonPath("$.[*].eventId").value(hasItem(DEFAULT_EVENT_ID))
            .jsonPath("$.[*].ticketId").value(hasItem(DEFAULT_TICKET_ID))
            .jsonPath("$.[*].billingDate").value(hasItem(DEFAULT_BILLING_DATE.toString()))
            .jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL))
            .jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.intValue()))
            .jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue()));
    }
    
    @Test
    public void getUserBilling() {
        // Initialize the database
        userBillingRepository.save(userBilling).block();

        // Get the userBilling
        webTestClient.get().uri("/api/user-billings/{id}", userBilling.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").value(is(userBilling.getId()))
            .jsonPath("$.eventId").value(is(DEFAULT_EVENT_ID))
            .jsonPath("$.ticketId").value(is(DEFAULT_TICKET_ID))
            .jsonPath("$.billingDate").value(is(DEFAULT_BILLING_DATE.toString()))
            .jsonPath("$.email").value(is(DEFAULT_EMAIL))
            .jsonPath("$.discount").value(is(DEFAULT_DISCOUNT.intValue()))
            .jsonPath("$.price").value(is(DEFAULT_PRICE.intValue()));
    }
    @Test
    public void getNonExistingUserBilling() {
        // Get the userBilling
        webTestClient.get().uri("/api/user-billings/{id}", Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isNotFound();
    }

    @Test
    public void updateUserBilling() throws Exception {
        // Initialize the database
        userBillingRepository.save(userBilling).block();

        int databaseSizeBeforeUpdate = userBillingRepository.findAll().collectList().block().size();

        // Update the userBilling
        UserBilling updatedUserBilling = userBillingRepository.findById(userBilling.getId()).block();
        updatedUserBilling
            .eventId(UPDATED_EVENT_ID)
            .ticketId(UPDATED_TICKET_ID)
            .billingDate(UPDATED_BILLING_DATE)
            .email(UPDATED_EMAIL)
            .discount(UPDATED_DISCOUNT)
            .price(UPDATED_PRICE);

        webTestClient.put().uri("/api/user-billings")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedUserBilling))
            .exchange()
            .expectStatus().isOk();

        // Validate the UserBilling in the database
        List<UserBilling> userBillingList = userBillingRepository.findAll().collectList().block();
        assertThat(userBillingList).hasSize(databaseSizeBeforeUpdate);
        UserBilling testUserBilling = userBillingList.get(userBillingList.size() - 1);
        assertThat(testUserBilling.getEventId()).isEqualTo(UPDATED_EVENT_ID);
        assertThat(testUserBilling.getTicketId()).isEqualTo(UPDATED_TICKET_ID);
        assertThat(testUserBilling.getBillingDate()).isEqualTo(UPDATED_BILLING_DATE);
        assertThat(testUserBilling.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUserBilling.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testUserBilling.getPrice()).isEqualTo(UPDATED_PRICE);
    }

    @Test
    public void updateNonExistingUserBilling() throws Exception {
        int databaseSizeBeforeUpdate = userBillingRepository.findAll().collectList().block().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient.put().uri("/api/user-billings")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(userBilling))
            .exchange()
            .expectStatus().isBadRequest();

        // Validate the UserBilling in the database
        List<UserBilling> userBillingList = userBillingRepository.findAll().collectList().block();
        assertThat(userBillingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteUserBilling() {
        // Initialize the database
        userBillingRepository.save(userBilling).block();

        int databaseSizeBeforeDelete = userBillingRepository.findAll().collectList().block().size();

        // Delete the userBilling
        webTestClient.delete().uri("/api/user-billings/{id}", userBilling.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isNoContent();

        // Validate the database contains one less item
        List<UserBilling> userBillingList = userBillingRepository.findAll().collectList().block();
        assertThat(userBillingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
