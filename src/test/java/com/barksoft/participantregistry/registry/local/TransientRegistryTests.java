package com.barksoft.participantregistry.registry.local;

import com.barksoft.participantregistry.TestConstants;
import com.barksoft.participantregistry.registry.Registry;
import com.barksoft.participantregistry.serialization.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testing strategy: for each method on {@link Registry}, test when:
 *  1) a mapping already exists for the specified id, and
 *  2) a mapping does not already exist for the specified id.
 *
 * Additionally, when the result of a load is non-null we validate the retrieved {@link Participant} details.
 */
public class TransientRegistryTests {
    private Registry registry;

    @BeforeEach
    public void setup() {
        registry = new TransientRegistry();
    }

    @Test
    public void testAddParticipantDoesNotExist() {
        boolean result = registry.addParticipant(TestConstants.DEFAULT_TEST_ID, TestConstants.DEFAULT_TEST_PARTICIPANT);
        assertThat(result).isTrue();
    }

    @Test
    public void testAddParticipantAlreadyExists() {
        boolean result = registry.addParticipant(TestConstants.DEFAULT_TEST_ID, TestConstants.DEFAULT_TEST_PARTICIPANT);
        assertThat(result).isTrue();
        boolean updatedResult = registry.addParticipant(TestConstants.DEFAULT_TEST_ID, TestConstants.DEFAULT_TEST_PARTICIPANT);
        assertThat(updatedResult).isFalse();
    }

    @Test
    public void testLoadParticipantDoesNotExist() {
        Participant result = registry.loadParticipant(TestConstants.DEFAULT_TEST_ID);
        assertThat(result).isNull();
    }

    @Test
    public void testLoadParticipantExists() {
        boolean result = registry.addParticipant(TestConstants.DEFAULT_TEST_ID, TestConstants.DEFAULT_TEST_PARTICIPANT);
        assertThat(result).isTrue();
        Participant participantResult = registry.loadParticipant(TestConstants.DEFAULT_TEST_ID);
        assertThat(participantResult.name()).isEqualTo(TestConstants.DEFAULT_TEST_PARTICIPANT_NAME);
        assertThat(participantResult.dateOfBirth()).isEqualTo(TestConstants.DEFAULT_TEST_PARTICIPANT_DOB);
        assertThat(participantResult.phoneNumber()).isEqualTo(TestConstants.DEFAULT_TEST_PARTICIPANT_PHONE);
        assertThat(participantResult.address()).isEqualTo(TestConstants.DEFAULT_TEST_PARTICIPANT_ADDRESS);
    }

    @Test
    public void testUpdateParticipantDoesNotExist() {
        boolean result = registry.updateParticipant(TestConstants.DEFAULT_TEST_ID, TestConstants.UPDATED_TEST_PARTICIPANT);
        assertThat(result).isFalse();
    }

    @Test
    public void testUpdatedParticipantExists() {
        boolean result = registry.addParticipant(TestConstants.DEFAULT_TEST_ID, TestConstants.DEFAULT_TEST_PARTICIPANT);
        assertThat(result).isTrue();
        boolean updateResult = registry.updateParticipant(TestConstants.DEFAULT_TEST_ID, TestConstants.UPDATED_TEST_PARTICIPANT);
        assertThat(updateResult).isTrue();
        Participant participantResult = registry.loadParticipant(TestConstants.DEFAULT_TEST_ID);
        assertThat(participantResult.name()).isEqualTo(TestConstants.UPDATED_TEST_PARTICIPANT_NAME);
        assertThat(participantResult.dateOfBirth()).isEqualTo(TestConstants.UPDATED_TEST_PARTICIPANT_DOB);
        assertThat(participantResult.phoneNumber()).isEqualTo(TestConstants.UPDATED_TEST_PARTICIPANT_PHONE);
        assertThat(participantResult.address()).isEqualTo(TestConstants.UPDATED_TEST_PARTICIPANT_ADDRESS);
    }

    @Test
    public void testDeleteParticipantDoesNotExist() {
        boolean deletionResult = registry.deleteParticipant(TestConstants.DEFAULT_TEST_ID);
        assertThat(deletionResult).isFalse();
    }

    @Test
    public void testDeleteParticipantExists() {
        boolean result = registry.addParticipant(TestConstants.DEFAULT_TEST_ID, TestConstants.DEFAULT_TEST_PARTICIPANT);
        assertThat(result).isTrue();
        boolean deletionResult = registry.deleteParticipant(TestConstants.DEFAULT_TEST_ID);
        assertThat(deletionResult).isTrue();
    }
}
