package com.heech.member.core.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @Column(length = 5)
    private String zipcode;
    private String address;
    @Column(name = "detail_address")
    private String detail;

    public Address(String zipcode, String address, String detail) {
        this.zipcode = zipcode;
        this.address = address;
        this.detail = detail;
    }

    public String fullAddress() {
        return "(" + this.zipcode + ") " + this.address + " " + this.detail;
    }
}
