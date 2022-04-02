package com.barksoft.participantregistry;

import com.barksoft.participantregistry.serialization.Participant;

public interface ParticipantRegistryService {
    /**
     * Stores a new participant in the registry.
     * @param participant details of the participant to be stored.
     * @return unique reference number identifying the stored participant.
     */
    String createParticipant(Participant participant);

    /**
     * Loads participant details based on the provided id.
     * @param id unique id of participant to load.
     * @return details of the requested participant.
     */
    Participant loadParticipant(String id);

    /**
     * Updates the stored details for a participant.
     * @param id unique id of the participant to update the details of.
     * @param participant updated details of the participant to store.
     * @return true if successful, false otherwise.
     */
    boolean updateParticipant(String id, Participant participant);

    /**
     * Deletes the entry for the specified participant.
     * @param id unique id for the participant to delete.
     * @return true if deletion was successful, false otherwise.
     */
    boolean deleteParticipant(String id);
}
