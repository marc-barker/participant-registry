package com.barksoft.participantregistry;

import com.barksoft.participantregistry.serialization.Participant;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class})
@AutoConfigureWebTestClient
class ParticipantRegistryApplicationTests {
    @Autowired
    private WebTestClient testClient;

    private ObjectMapper objectMapper = new ObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

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
