package com.api.models.response;

import com.api.models.common.Data;

public record UpdateEntityResponse(String id, String name, String updatedAt, Data data) {
}
