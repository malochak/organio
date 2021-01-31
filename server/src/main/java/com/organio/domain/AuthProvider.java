package com.organio.domain;

public enum  AuthProvider {
    LOCAL,
    GOOGLE;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
