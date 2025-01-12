package com.api.models.request;

public record SignupRequest(String username, String password, String email, String firstName, String lastName, String mobileNumber) {

    public static class Builder{
        private String username;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private String mobileNumber;

        public Builder setUserName(String username){
            this.username=username;
            return this;
        }

        public Builder setPassword(String password){
            this.password = password;
            return this;
        }

        public Builder setEmail(String email){
            this.email = email;
            return this;
        }

        public Builder setFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder setMobileNumber(String mobileNumber){
            this.mobileNumber = mobileNumber;
            return this;
        }

        public SignupRequest build(){
            return new SignupRequest(username, password, email, firstName, lastName, mobileNumber);
        }
    }
}
