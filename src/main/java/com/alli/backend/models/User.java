package com.alli.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    @Field(name = "phone_no")
    private String phoneNum;

    @Field(name = "phone_no_alt")
    private String phoneNumAlt;

    @Field(name = "citizen_id")
    private String citizenId;

    private String email;

    private Role role = Role.USER;

    //Agent Only Var
    private String company;
    //Location for agent

}
