package com.example.YBO.status;


//this enum is for jobRequest entity
public enum RequestStatus {

    APPROVED,
    REJECTED,
    PENDING;

    @Override
    public String toString() {
        return name();
    }
}
