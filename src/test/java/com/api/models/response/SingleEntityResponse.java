package com.api.models.response;

import com.api.models.common.Data;

public record SingleEntityResponse(String id, String name, Data data){}