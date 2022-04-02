package com.barksoft.participantregistry.serialization;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.LocalDate;

@Value.Immutable
@JsonSerialize(as = ImmutableParticipant.class)
@JsonDeserialize(as = ImmutableParticipant.class, builder = ImmutableParticipant.Builder.class)
public interface Participant {
    @JsonProperty
    String name();

    @JsonProperty(value = "dob")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate dateOfBirth();

    @JsonProperty
    String phoneNumber();

    @JsonProperty
    Address address();
}
