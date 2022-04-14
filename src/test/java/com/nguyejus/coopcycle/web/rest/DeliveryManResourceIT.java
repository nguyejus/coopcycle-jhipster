package com.nguyejus.coopcycle.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nguyejus.coopcycle.IntegrationTest;
import com.nguyejus.coopcycle.domain.DeliveryMan;
import com.nguyejus.coopcycle.repository.DeliveryManRepository;
import com.nguyejus.coopcycle.service.dto.DeliveryManDTO;
import com.nguyejus.coopcycle.service.mapper.DeliveryManMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link DeliveryManResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DeliveryManResourceIT {

    private static final Integer DEFAULT_I_D = 1;
    private static final Integer UPDATED_I_D = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SURNAME = "AAAAAAAAAA";
    private static final String UPDATED_SURNAME = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_VEHICULE = "AAAAAAAAAA";
    private static final String UPDATED_VEHICULE = "BBBBBBBBBB";

    private static final Float DEFAULT_LATITUDE = 1F;
    private static final Float UPDATED_LATITUDE = 2F;

    private static final Float DEFAULT_LONGITUDE = 1F;
    private static final Float UPDATED_LONGITUDE = 2F;

    private static final String ENTITY_API_URL = "/api/delivery-men";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DeliveryManRepository deliveryManRepository;

    @Autowired
    private DeliveryManMapper deliveryManMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDeliveryManMockMvc;

    private DeliveryMan deliveryMan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryMan createEntity(EntityManager em) {
        DeliveryMan deliveryMan = new DeliveryMan()
            .iD(DEFAULT_I_D)
            .name(DEFAULT_NAME)
            .surname(DEFAULT_SURNAME)
            .telephone(DEFAULT_TELEPHONE)
            .vehicule(DEFAULT_VEHICULE)
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE);
        return deliveryMan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeliveryMan createUpdatedEntity(EntityManager em) {
        DeliveryMan deliveryMan = new DeliveryMan()
            .iD(UPDATED_I_D)
            .name(UPDATED_NAME)
            .surname(UPDATED_SURNAME)
            .telephone(UPDATED_TELEPHONE)
            .vehicule(UPDATED_VEHICULE)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE);
        return deliveryMan;
    }

    @BeforeEach
    public void initTest() {
        deliveryMan = createEntity(em);
    }

    @Test
    @Transactional
    void createDeliveryMan() throws Exception {
        int databaseSizeBeforeCreate = deliveryManRepository.findAll().size();
        // Create the DeliveryMan
        DeliveryManDTO deliveryManDTO = deliveryManMapper.toDto(deliveryMan);
        restDeliveryManMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deliveryManDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DeliveryMan in the database
        List<DeliveryMan> deliveryManList = deliveryManRepository.findAll();
        assertThat(deliveryManList).hasSize(databaseSizeBeforeCreate + 1);
        DeliveryMan testDeliveryMan = deliveryManList.get(deliveryManList.size() - 1);
        assertThat(testDeliveryMan.getiD()).isEqualTo(DEFAULT_I_D);
        assertThat(testDeliveryMan.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDeliveryMan.getSurname()).isEqualTo(DEFAULT_SURNAME);
        assertThat(testDeliveryMan.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testDeliveryMan.getVehicule()).isEqualTo(DEFAULT_VEHICULE);
        assertThat(testDeliveryMan.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testDeliveryMan.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
    }

    @Test
    @Transactional
    void createDeliveryManWithExistingId() throws Exception {
        // Create the DeliveryMan with an existing ID
        deliveryMan.setId(1L);
        DeliveryManDTO deliveryManDTO = deliveryManMapper.toDto(deliveryMan);

        int databaseSizeBeforeCreate = deliveryManRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeliveryManMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deliveryManDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryMan in the database
        List<DeliveryMan> deliveryManList = deliveryManRepository.findAll();
        assertThat(deliveryManList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkiDIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryManRepository.findAll().size();
        // set the field null
        deliveryMan.setiD(null);

        // Create the DeliveryMan, which fails.
        DeliveryManDTO deliveryManDTO = deliveryManMapper.toDto(deliveryMan);

        restDeliveryManMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deliveryManDTO))
            )
            .andExpect(status().isBadRequest());

        List<DeliveryMan> deliveryManList = deliveryManRepository.findAll();
        assertThat(deliveryManList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLatitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryManRepository.findAll().size();
        // set the field null
        deliveryMan.setLatitude(null);

        // Create the DeliveryMan, which fails.
        DeliveryManDTO deliveryManDTO = deliveryManMapper.toDto(deliveryMan);

        restDeliveryManMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deliveryManDTO))
            )
            .andExpect(status().isBadRequest());

        List<DeliveryMan> deliveryManList = deliveryManRepository.findAll();
        assertThat(deliveryManList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLongitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = deliveryManRepository.findAll().size();
        // set the field null
        deliveryMan.setLongitude(null);

        // Create the DeliveryMan, which fails.
        DeliveryManDTO deliveryManDTO = deliveryManMapper.toDto(deliveryMan);

        restDeliveryManMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deliveryManDTO))
            )
            .andExpect(status().isBadRequest());

        List<DeliveryMan> deliveryManList = deliveryManRepository.findAll();
        assertThat(deliveryManList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDeliveryMen() throws Exception {
        // Initialize the database
        deliveryManRepository.saveAndFlush(deliveryMan);

        // Get all the deliveryManList
        restDeliveryManMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deliveryMan.getId().intValue())))
            .andExpect(jsonPath("$.[*].iD").value(hasItem(DEFAULT_I_D)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].surname").value(hasItem(DEFAULT_SURNAME)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].vehicule").value(hasItem(DEFAULT_VEHICULE)))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())));
    }

    @Test
    @Transactional
    void getDeliveryMan() throws Exception {
        // Initialize the database
        deliveryManRepository.saveAndFlush(deliveryMan);

        // Get the deliveryMan
        restDeliveryManMockMvc
            .perform(get(ENTITY_API_URL_ID, deliveryMan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(deliveryMan.getId().intValue()))
            .andExpect(jsonPath("$.iD").value(DEFAULT_I_D))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.surname").value(DEFAULT_SURNAME))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.vehicule").value(DEFAULT_VEHICULE))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingDeliveryMan() throws Exception {
        // Get the deliveryMan
        restDeliveryManMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDeliveryMan() throws Exception {
        // Initialize the database
        deliveryManRepository.saveAndFlush(deliveryMan);

        int databaseSizeBeforeUpdate = deliveryManRepository.findAll().size();

        // Update the deliveryMan
        DeliveryMan updatedDeliveryMan = deliveryManRepository.findById(deliveryMan.getId()).get();
        // Disconnect from session so that the updates on updatedDeliveryMan are not directly saved in db
        em.detach(updatedDeliveryMan);
        updatedDeliveryMan
            .iD(UPDATED_I_D)
            .name(UPDATED_NAME)
            .surname(UPDATED_SURNAME)
            .telephone(UPDATED_TELEPHONE)
            .vehicule(UPDATED_VEHICULE)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE);
        DeliveryManDTO deliveryManDTO = deliveryManMapper.toDto(updatedDeliveryMan);

        restDeliveryManMockMvc
            .perform(
                put(ENTITY_API_URL_ID, deliveryManDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryManDTO))
            )
            .andExpect(status().isOk());

        // Validate the DeliveryMan in the database
        List<DeliveryMan> deliveryManList = deliveryManRepository.findAll();
        assertThat(deliveryManList).hasSize(databaseSizeBeforeUpdate);
        DeliveryMan testDeliveryMan = deliveryManList.get(deliveryManList.size() - 1);
        assertThat(testDeliveryMan.getiD()).isEqualTo(UPDATED_I_D);
        assertThat(testDeliveryMan.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDeliveryMan.getSurname()).isEqualTo(UPDATED_SURNAME);
        assertThat(testDeliveryMan.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testDeliveryMan.getVehicule()).isEqualTo(UPDATED_VEHICULE);
        assertThat(testDeliveryMan.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testDeliveryMan.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    void putNonExistingDeliveryMan() throws Exception {
        int databaseSizeBeforeUpdate = deliveryManRepository.findAll().size();
        deliveryMan.setId(count.incrementAndGet());

        // Create the DeliveryMan
        DeliveryManDTO deliveryManDTO = deliveryManMapper.toDto(deliveryMan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliveryManMockMvc
            .perform(
                put(ENTITY_API_URL_ID, deliveryManDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryManDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryMan in the database
        List<DeliveryMan> deliveryManList = deliveryManRepository.findAll();
        assertThat(deliveryManList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDeliveryMan() throws Exception {
        int databaseSizeBeforeUpdate = deliveryManRepository.findAll().size();
        deliveryMan.setId(count.incrementAndGet());

        // Create the DeliveryMan
        DeliveryManDTO deliveryManDTO = deliveryManMapper.toDto(deliveryMan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryManMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deliveryManDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryMan in the database
        List<DeliveryMan> deliveryManList = deliveryManRepository.findAll();
        assertThat(deliveryManList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDeliveryMan() throws Exception {
        int databaseSizeBeforeUpdate = deliveryManRepository.findAll().size();
        deliveryMan.setId(count.incrementAndGet());

        // Create the DeliveryMan
        DeliveryManDTO deliveryManDTO = deliveryManMapper.toDto(deliveryMan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryManMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deliveryManDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DeliveryMan in the database
        List<DeliveryMan> deliveryManList = deliveryManRepository.findAll();
        assertThat(deliveryManList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDeliveryManWithPatch() throws Exception {
        // Initialize the database
        deliveryManRepository.saveAndFlush(deliveryMan);

        int databaseSizeBeforeUpdate = deliveryManRepository.findAll().size();

        // Update the deliveryMan using partial update
        DeliveryMan partialUpdatedDeliveryMan = new DeliveryMan();
        partialUpdatedDeliveryMan.setId(deliveryMan.getId());

        partialUpdatedDeliveryMan.name(UPDATED_NAME).surname(UPDATED_SURNAME).telephone(UPDATED_TELEPHONE).latitude(UPDATED_LATITUDE);

        restDeliveryManMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDeliveryMan.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDeliveryMan))
            )
            .andExpect(status().isOk());

        // Validate the DeliveryMan in the database
        List<DeliveryMan> deliveryManList = deliveryManRepository.findAll();
        assertThat(deliveryManList).hasSize(databaseSizeBeforeUpdate);
        DeliveryMan testDeliveryMan = deliveryManList.get(deliveryManList.size() - 1);
        assertThat(testDeliveryMan.getiD()).isEqualTo(DEFAULT_I_D);
        assertThat(testDeliveryMan.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDeliveryMan.getSurname()).isEqualTo(UPDATED_SURNAME);
        assertThat(testDeliveryMan.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testDeliveryMan.getVehicule()).isEqualTo(DEFAULT_VEHICULE);
        assertThat(testDeliveryMan.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testDeliveryMan.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
    }

    @Test
    @Transactional
    void fullUpdateDeliveryManWithPatch() throws Exception {
        // Initialize the database
        deliveryManRepository.saveAndFlush(deliveryMan);

        int databaseSizeBeforeUpdate = deliveryManRepository.findAll().size();

        // Update the deliveryMan using partial update
        DeliveryMan partialUpdatedDeliveryMan = new DeliveryMan();
        partialUpdatedDeliveryMan.setId(deliveryMan.getId());

        partialUpdatedDeliveryMan
            .iD(UPDATED_I_D)
            .name(UPDATED_NAME)
            .surname(UPDATED_SURNAME)
            .telephone(UPDATED_TELEPHONE)
            .vehicule(UPDATED_VEHICULE)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE);

        restDeliveryManMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDeliveryMan.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDeliveryMan))
            )
            .andExpect(status().isOk());

        // Validate the DeliveryMan in the database
        List<DeliveryMan> deliveryManList = deliveryManRepository.findAll();
        assertThat(deliveryManList).hasSize(databaseSizeBeforeUpdate);
        DeliveryMan testDeliveryMan = deliveryManList.get(deliveryManList.size() - 1);
        assertThat(testDeliveryMan.getiD()).isEqualTo(UPDATED_I_D);
        assertThat(testDeliveryMan.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDeliveryMan.getSurname()).isEqualTo(UPDATED_SURNAME);
        assertThat(testDeliveryMan.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testDeliveryMan.getVehicule()).isEqualTo(UPDATED_VEHICULE);
        assertThat(testDeliveryMan.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testDeliveryMan.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    void patchNonExistingDeliveryMan() throws Exception {
        int databaseSizeBeforeUpdate = deliveryManRepository.findAll().size();
        deliveryMan.setId(count.incrementAndGet());

        // Create the DeliveryMan
        DeliveryManDTO deliveryManDTO = deliveryManMapper.toDto(deliveryMan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliveryManMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, deliveryManDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(deliveryManDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryMan in the database
        List<DeliveryMan> deliveryManList = deliveryManRepository.findAll();
        assertThat(deliveryManList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDeliveryMan() throws Exception {
        int databaseSizeBeforeUpdate = deliveryManRepository.findAll().size();
        deliveryMan.setId(count.incrementAndGet());

        // Create the DeliveryMan
        DeliveryManDTO deliveryManDTO = deliveryManMapper.toDto(deliveryMan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryManMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(deliveryManDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeliveryMan in the database
        List<DeliveryMan> deliveryManList = deliveryManRepository.findAll();
        assertThat(deliveryManList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDeliveryMan() throws Exception {
        int databaseSizeBeforeUpdate = deliveryManRepository.findAll().size();
        deliveryMan.setId(count.incrementAndGet());

        // Create the DeliveryMan
        DeliveryManDTO deliveryManDTO = deliveryManMapper.toDto(deliveryMan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliveryManMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(deliveryManDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DeliveryMan in the database
        List<DeliveryMan> deliveryManList = deliveryManRepository.findAll();
        assertThat(deliveryManList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDeliveryMan() throws Exception {
        // Initialize the database
        deliveryManRepository.saveAndFlush(deliveryMan);

        int databaseSizeBeforeDelete = deliveryManRepository.findAll().size();

        // Delete the deliveryMan
        restDeliveryManMockMvc
            .perform(delete(ENTITY_API_URL_ID, deliveryMan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DeliveryMan> deliveryManList = deliveryManRepository.findAll();
        assertThat(deliveryManList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
