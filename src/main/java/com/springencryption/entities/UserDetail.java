package com.springencryption.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.springencryption.converters.StringAttributeConvert;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity(name = "users")
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDetail {
    @Id
    @GeneratedValue(generator = "uuidv4")
    @GenericGenerator(name = "uuidv4", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(length = 200, nullable = false)
    private String name;


    /**
     * Second example using ColumnTransformer annotation
     */
    @ColumnTransformer(
        read = "AES_DECRYPT(UNHEX(email), 'VVLQVUKFKLJUKDXJ')",
        write = "HEX(AES_ENCRYPT(?, 'VVLQVUKFKLJUKDXJ'))"
    )
    @Column(length = 200, nullable = false, unique = true)
    private String email;

    /**
     * First example using converter class
     */
    @Convert(converter = StringAttributeConvert.class)
    @Column(length = 200, nullable = false, unique = true)
    private String cardNumber;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expirationDate;
}
