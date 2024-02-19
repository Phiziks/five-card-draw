package com.ishmael.fivecarddraw.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RetrieveDeckRequest {
    @JsonProperty("deck_count")
    int count;
}
