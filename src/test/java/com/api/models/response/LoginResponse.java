package com.api.models.response;
import java.util.List;

public record LoginResponse(String token, String type, int id, String username, String email, List<String> roles) {
}
