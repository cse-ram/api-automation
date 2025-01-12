package com.api.models.response;

public record UserProfileResponse(int id, String username, String email, String firstName, String lastName,  String mobileNumber) {
}