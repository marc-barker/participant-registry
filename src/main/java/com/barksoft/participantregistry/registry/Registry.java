package com.barksoft.participantregistry.registry;

import com.barksoft.participantregistry.serialization.Participant;

/**
 * Service responsible for CRUD operations on {@link Participant} information. Participant information is stored using
 * unique keys (referred to as id).
 */
public interface Registry {
    /**
     * Stores the provided {@link Participant} in the {@link Registry} with the provided id key.
     * @param id unique id of the participant to store.
     * @param participant details of the participant to store.
     * @return true if store operation was successful, false otherwise.
     */
    boolean addParticipant(String id, Participant participant);

    /**
     * Loads the {@link Participant} stored for the given id, or null if there is no mapping for the id.
     * @param id unique id for the participant to retrieve.
     * @return participant records stored for the specified id.
     */
    Participant loadParticipant(String id);

    /**
     * Updates the stored {@link Participant} details for the specified id key.
     * @param id unique id of participant whose details should be updated.
     * @param participant new participant details to store for the id.
     * @return true if the mapping existed and the details have been updated, false otherwise.
     */
    boolean updateParticipant(String id, Participant participant);

    /**
     * Deletes the stored participant information for the specified id.
     * @param id unique id of the participant to delete details for.
     * @return true if records were deleted, false otherwise.
     */
    boolean deleteParticipant(String id);
}
