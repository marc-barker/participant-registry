package com.barksoft.participantregistry.uid;

/**
 * Service responsible for creating unique identifiers (uids).
 */
public interface UidService {
    /**
     * Gets a new, unique id for a participant. The format of the reference number is determined by the implementation.
     * @return String containing the unique reference number
     */
    String getUid();
}
