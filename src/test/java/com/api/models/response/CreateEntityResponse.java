package com.api.models.response;

import com.api.models.common.Data;

public record CreateEntityResponse(String id, String name, String createdAt, Data data) {
}

