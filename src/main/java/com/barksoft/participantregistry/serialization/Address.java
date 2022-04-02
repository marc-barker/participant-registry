package com.barksoft.participantregistry.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@JsonSerialize(as = ImmutableAddress.class)
@JsonDeserialize(as = ImmutableAddress.class, builder = ImmutableAddress.Builder.class)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public interface Address {
    @JsonProperty
    Optional<String> number();

    @JsonProperty
    Optional<String> street();

    @JsonProperty
    Optional<String> suburb();

    @JsonProperty
    Optional<String> city();

    @JsonProperty
    String state();

    @JsonProperty
    String postcode();

    @JsonProperty
    String country();
}
