package com.example.employeemanagementsystem.model.dto;

public class EmployerPatchRequest {

    private String name;

    private String email;

    public EmployerPatchRequest(String nameString,String email) {
        this.name = name;
        this.email = email;
    }

    public EmployerPatchRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmployerPatchRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
