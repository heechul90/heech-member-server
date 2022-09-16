package com.heech.member.core.domain;

import com.mysema.commons.lang.Assert;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mobile {

    @Column(length = 3)
    private String mobileNumberFirst;
    @Column(length = 4)
    private String mobileNumberMiddle;
    @Column(length = 4)
    private String mobileNumberLast;

    public Mobile(String mobileNumberFirst, String mobileNumberMiddle, String mobileNumberLast) {
        this.mobileNumberFirst = mobileNumberFirst;
        this.mobileNumberMiddle = mobileNumberMiddle;
        this.mobileNumberLast = mobileNumberLast;
    }

    public String fullMobileNumber() {
        return this.mobileNumberFirst + "-" + this.mobileNumberMiddle + "-" + this.mobileNumberLast;
    }
}
