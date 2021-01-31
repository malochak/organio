package com.organio.oauth2.userinfo;

import java.util.Map;

public class GoogleOAuth2UserInfo extends OAuth2UserInfo {
    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String id() {
        return (String) attributes.get("sub");
    }

    @Override
    public String name() {
        return (String) attributes.get("name");
    }

    @Override
    public String email() {
        return (String) attributes.get("email");
    }

    @Override
    public String imageUrl() {
        return (String) attributes.get("picture");
    }
}
