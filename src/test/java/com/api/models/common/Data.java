package com.api.models.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public record Data(int year, double price, @JsonProperty("CPU model") String cpuModel, @JsonProperty("Hard disk size") String hardDiskSize, Optional<String> color) {
}
