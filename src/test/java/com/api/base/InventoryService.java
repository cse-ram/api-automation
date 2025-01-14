package com.api.base;

import com.api.models.request.CreateEntity;
import io.restassured.response.Response;

public class InventoryService extends BaseService{
    private static final String BASE_PATH="/objects";

    public Response CreateEntity(CreateEntity payload){
        return postRequest(payload, BASE_PATH);
    }

    public Response getEntity(String id){
        return getRequest(String.format("%s/%s", BASE_PATH, id));
    }

    public Response deleteEntity(String id){
        return deleteRequest(String.format("%s/%s", BASE_PATH, id));
    }
}
