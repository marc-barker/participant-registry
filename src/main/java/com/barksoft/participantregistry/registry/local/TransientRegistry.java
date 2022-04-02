package com.barksoft.participantregistry.registry.local;

import com.barksoft.participantregistry.registry.Registry;
import com.barksoft.participantregistry.serialization.Participant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple transient implementation of {@link Registry} that stores entries in a {@link ConcurrentHashMap}.
 */
public class TransientRegistry implements Registry {
    private final Map<String, Participant> idToParticipantMap;

    public TransientRegistry() {
        idToParticipantMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean addParticipant(String id, Participant participant) {
        Participant result = idToParticipantMap.putIfAbsent(id, participant);
        return result == null;
    }

    @Override
    public Participant loadParticipant(String id) {
        return idToParticipantMap.get(id);
    }

    @Override
    public boolean updateParticipant(String id, Participant participant) {
        Participant result = idToParticipantMap.put(id, participant);
        return result != null;
    }

    @Override
    public boolean deleteParticipant(String id) {
        Participant result = idToParticipantMap.remove(id);
        return result != null;
    }
}
