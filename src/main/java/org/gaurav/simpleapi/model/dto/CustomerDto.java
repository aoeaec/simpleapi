package org.gaurav.simpleapi.model.dto;




public record CustomerDto(String firstName,
                          String lastName,
                          String email,
                          String location) { }
