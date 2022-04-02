package com.barksoft.participantregistry.uid.local;

import com.barksoft.participantregistry.uid.UidService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Basic tests to check uid generation is 1) unique, and 2) not null. More comprehensive testing would be expected to be
 * done in the codebase of any service/library that is used for uid generation in an implementation of {@link UidService}.
 */
public class TransientUidServiceTests {
    private UidService uidService;

    @BeforeEach
    public void setup() {
        uidService = new TransientUidService();
    }

    @Test
    public void testGetUidIsNotNull() {
        String uid = uidService.getUid();
        assertThat(uid).isNotNull();
    }

    @Test
    public void testGetUidMultipleTimesYieldsUniqueValues() {
        String uid1 = uidService.getUid();
        String uid2 = uidService.getUid();
        assertThat(uid1).isNotEqualTo(uid2);
    }
}
