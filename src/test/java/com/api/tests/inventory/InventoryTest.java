package com.api.tests.inventory;

import com.api.base.InventoryService;
import com.api.models.common.Data;
import com.api.models.request.CreateEntity;
import com.api.models.response.CreateEntityResponse;
import com.api.models.response.DeleteEntityResponse;
import com.api.models.response.SingleEntityResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class InventoryTest {
    private CreateEntity payload;
    private InventoryService inventoryService;
    private String id;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        Data data = new Data(2024, 2500.45, "M3", "512 GB", null);
        payload = new CreateEntity("Apple MacBook Pro", data);
        inventoryService = new InventoryService();
    }


    @Test(priority = 1, description = "Verify that user is able to create a inventory", groups = {"inventory"})
    public void createInventoryTest() {

        Response response = inventoryService.CreateEntity(payload);
        Assert.assertEquals(response.statusCode(), 200);

        CreateEntityResponse createEntityResponse = response.as(CreateEntityResponse.class);

        id = createEntityResponse.id();

        Assert.assertEquals(createEntityResponse.name(), payload.name());
        Assert.assertEquals(createEntityResponse.data().year(), payload.data().year());
        Assert.assertEquals(createEntityResponse.data().cpuModel(), payload.data().cpuModel());
        Assert.assertEquals(createEntityResponse.data().color(), payload.data().color());
        Assert.assertEquals(createEntityResponse.data().hardDiskSize(), payload.data().hardDiskSize());
        Assert.assertEquals(createEntityResponse.data().price(), payload.data().price());

    }

    @Test(priority = 2, description = "Verify created inventory data match with get api response", groups = {"inventory"}, dependsOnMethods = {"createInventoryTest"})
    public void getInventoryTest() {

        Response response = inventoryService.getEntity(id);
        Assert.assertEquals(response.statusCode(), 200);

        SingleEntityResponse singleEntityResponse = response.as(SingleEntityResponse.class);

        Assert.assertEquals(singleEntityResponse.id(), id);
        Assert.assertEquals(singleEntityResponse.name(), payload.name());
        Assert.assertEquals(singleEntityResponse.data().year(), payload.data().year());
        Assert.assertEquals(singleEntityResponse.data().cpuModel(), payload.data().cpuModel());
        Assert.assertEquals(singleEntityResponse.data().color(), payload.data().color());
        Assert.assertEquals(singleEntityResponse.data().hardDiskSize(), payload.data().hardDiskSize());
        Assert.assertEquals(singleEntityResponse.data().price(), payload.data().price());
    }

    @Test(priority = 3, description = "Verify delete inventory functionality", groups = {"inventory"}, dependsOnMethods = {"createInventoryTest"})
    public void deleteInventoryTest() {
        Response response = inventoryService.deleteEntity(id);

        Assert.assertEquals(response.statusCode(), 200);

        DeleteEntityResponse deleteEntityResponse = response.as(DeleteEntityResponse.class);

        String expectedMessage = String.format("Object with id = %s has been deleted.", id);
        Assert.assertEquals(deleteEntityResponse.message(), expectedMessage);

    }
}
