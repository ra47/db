package com.alli.backend.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "products")
public class Product {

    @Id
    private String Id;

    private String title;

    private String description;

    private String Budget;

    private String company;

    @Field(name = "product_name")
    private String productName;

    //enum
    @Field(name = "product_type")
    private String productType;
    //enum
    @Field(name = "product_range")
    private String productRange;

    private int ipd;

    private int opd;

    private int death;

    private int room;

    @Field(name = "annual_payment")
    private int annualPayment;

    @Field(name = "trimester_payment")
    private int trimesterPayment;

    @Field(name = "monthly_payment")
    private int monthlyPayment;

    @Field(name = "retirement_package_one_time")
    private int retirementOneTime;

    @Field(name = "retirement_package_monthly_salary")
    private int retirementMonthly;
}
