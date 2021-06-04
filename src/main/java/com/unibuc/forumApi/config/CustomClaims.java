package com.unibuc.forumApi.config;

public enum CustomClaims {
    USERNAME("username");
    private final String text;

    CustomClaims(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
