package com.barksoft.participantregistry;

import com.barksoft.participantregistry.serialization.Participant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.barksoft.participantregistry.ParticipantRegistryApplication.BASE_PATH;
import static com.barksoft.participantregistry.ParticipantRegistryApplication.ID_PATH;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Basic testing of all endpoints in {@link ParticipantRegistryService}. This class mainly serves as a check that
 * the web service will respond to basic requests. Because this spins up a web server for the tests, edge case and
 * exhaustive testing should ideally be put in unit tests for underlying service implementations to reduce test runtime
 * as the project grows (see {@link com.barksoft.participantregistry.registry.local.TransientRegistryTests} and
 * {@link com.barksoft.participantregistry.uid.local.TransientUidServiceTests} for examples).
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class})
@AutoConfigureWebTestClient
class ParticipantRegistryApplicationTests {
    @Autowired
    private WebTestClient testClient;

    @Test
    void testCreateParticipant() {
        testClient.post().uri(BASE_PATH)
                .bodyValue(TestConstants.DEFAULT_TEST_PARTICIPANT)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).value(uid -> assertThat(uid).isNotNull());
    }

    @Test
    void testLoadParticipant() {
        String storedUid = testClient.post().uri(BASE_PATH)
                .bodyValue(TestConstants.DEFAULT_TEST_PARTICIPANT)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).value(uid -> assertThat(uid).isNotNull())
                .returnResult().getResponseBody();
        testClient.get().uri(uriBuilder -> uriBuilder.path(ID_PATH).build(storedUid))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Participant.class).value(participant -> {
                    assertThat(participant.name()).isEqualTo(TestConstants.DEFAULT_TEST_PARTICIPANT_NAME);
                    assertThat(participant.dateOfBirth()).isEqualTo(TestConstants.DEFAULT_TEST_PARTICIPANT_DOB);
                    assertThat(participant.phoneNumber()).isEqualTo(TestConstants.DEFAULT_TEST_PARTICIPANT_PHONE);
                    assertThat(participant.address()).isEqualTo(TestConstants.DEFAULT_TEST_PARTICIPANT_ADDRESS);
                });
    }

    @Test
    void testUpdateParticipant() {
        String storedUid = testClient.post().uri(BASE_PATH)
                .bodyValue(TestConstants.DEFAULT_TEST_PARTICIPANT)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).value(uid -> assertThat(uid).isNotNull())
                .returnResult().getResponseBody();
        testClient.post().uri(uriBuilder -> uriBuilder.path(ID_PATH).build(storedUid))
                .bodyValue(TestConstants.UPDATED_TEST_PARTICIPANT)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk().expectBody(Boolean.class).isEqualTo(true);
        testClient.get().uri(uriBuilder -> uriBuilder.path(ID_PATH).build(storedUid))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Participant.class).value(participant -> {
                    assertThat(participant.name()).isEqualTo(TestConstants.UPDATED_TEST_PARTICIPANT_NAME);
                    assertThat(participant.dateOfBirth()).isEqualTo(TestConstants.UPDATED_TEST_PARTICIPANT_DOB);
                    assertThat(participant.phoneNumber()).isEqualTo(TestConstants.UPDATED_TEST_PARTICIPANT_PHONE);
                    assertThat(participant.address()).isEqualTo(TestConstants.UPDATED_TEST_PARTICIPANT_ADDRESS);
                });
    }

    @Test
    void testDeleteParticipant() {
        String storedUid = testClient.post().uri(BASE_PATH)
                .bodyValue(TestConstants.DEFAULT_TEST_PARTICIPANT)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).value(uid -> assertThat(uid).isNotNull())
                .returnResult().getResponseBody();
        testClient.delete().uri(uriBuilder -> uriBuilder.path(ID_PATH).build(storedUid))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Boolean.class).isEqualTo(true);
    }

}
