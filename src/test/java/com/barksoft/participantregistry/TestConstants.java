package com.barksoft.participantregistry;

import com.barksoft.participantregistry.serialization.Address;
import com.barksoft.participantregistry.serialization.ImmutableAddress;
import com.barksoft.participantregistry.serialization.ImmutableParticipant;
import com.barksoft.participantregistry.serialization.Participant;

import java.time.LocalDate;

public class TestConstants {
    public static final String DEFAULT_TEST_ID = "TESTING-123";
    public static final String DEFAULT_TEST_PARTICIPANT_NAME = "Test User";
  public static final LocalDate DEFAULT_TEST_PARTICIPANT_DOB = LocalDate.of(1950, 6, 1);
    public static final String DEFAULT_TEST_PARTICIPANT_PHONE = "+441234567890";
    public static final Address DEFAULT_TEST_PARTICIPANT_ADDRESS = ImmutableAddress.builder()
            .state("Greater London")
            .postcode("SW1")
            .country("UK")
            .build();
    public static final Participant DEFAULT_TEST_PARTICIPANT = ImmutableParticipant.builder()
                    .name(DEFAULT_TEST_PARTICIPANT_NAME)
                    .dateOfBirth(DEFAULT_TEST_PARTICIPANT_DOB)
                    .phoneNumber(DEFAULT_TEST_PARTICIPANT_PHONE)
                    .address(DEFAULT_TEST_PARTICIPANT_ADDRESS)
                    .build();
    public static final String UPDATED_TEST_PARTICIPANT_NAME = "Updated Test User";
    public static final LocalDate UPDATED_TEST_PARTICIPANT_DOB = LocalDate.of(1960, 6, 1);
    public static final String UPDATED_TEST_PARTICIPANT_PHONE = "+61412345678";
    public static final Address UPDATED_TEST_PARTICIPANT_ADDRESS = ImmutableAddress.builder()
                            .state("NSW")
                            .postcode("2000")
                            .country("Australia")
                            .build();
    public static final Participant UPDATED_TEST_PARTICIPANT = ImmutableParticipant.builder()
                                    .name(UPDATED_TEST_PARTICIPANT_NAME)
                                    .dateOfBirth(UPDATED_TEST_PARTICIPANT_DOB)
                                    .phoneNumber(UPDATED_TEST_PARTICIPANT_PHONE)
                                    .address(UPDATED_TEST_PARTICIPANT_ADDRESS)
                                    .build();
}
