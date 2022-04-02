package com.barksoft.participantregistry;

import com.barksoft.participantregistry.registry.Registry;
import com.barksoft.participantregistry.registry.local.TransientRegistry;
import com.barksoft.participantregistry.serialization.Participant;
import com.barksoft.participantregistry.uid.UidService;
import com.barksoft.participantregistry.uid.local.TransientUidService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@SpringBootApplication
@RestController
public class ParticipantRegistryApplication implements ParticipantRegistryService {
    public static final String BASE_PATH = "/";
    public static final String ID_PATH = "/{id}";

    private static final UidService UID_SERVICE = new TransientUidService();
    private static final Registry REGISTRY = new TransientRegistry();

    public static void main(String[] args) {
        SpringApplication.run(ParticipantRegistryApplication.class, args);
    }

    @PostMapping(path = BASE_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public String createParticipant(@RequestBody Participant participant) {
        String uid = UID_SERVICE.getUid();
        boolean storeResult = REGISTRY.addParticipant(uid, participant);
        if (!storeResult) {
            throw new RuntimeException("Unable to create new participant.");
        }
        return uid;
    }

    @GetMapping(path = ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public Participant loadParticipant(@PathVariable(value = "id") String id) {
        return REGISTRY.loadParticipant(id);
    }

    @PostMapping(path = ID_PATH, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public boolean updateParticipant(@PathVariable(value = "id") String id, @RequestBody Participant participant) {
        return REGISTRY.updateParticipant(id, participant);
    }

    @DeleteMapping(path = ID_PATH)
    @Override
    public boolean deleteParticipant(@PathVariable(value = "id") String id) {
        return REGISTRY.deleteParticipant(id);
    }
}
