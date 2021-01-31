package com.organio.oauth2.userinfo;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public abstract class OAuth2UserInfo {

    protected final Map<String, Object> attributes;

    public abstract String id();

    public abstract String name();

    public abstract String email();

    public abstract String imageUrl();
}
