package com.barksoft.participantregistry.uid.local;

import com.barksoft.participantregistry.uid.UidService;

import java.util.UUID;

/**
 * Simple implementation of {@link UidService} that returns random uids for the lifetime of the JVM.
 */
public class TransientUidService implements UidService {
    @Override
    public String getUid() {
        return UUID.randomUUID().toString();
    }
}
