package com.auth.oauth.repository;

import com.auth.oauth.model.RSAKeyPair;

import java.util.List;

public interface RSAKeyRepository {
    List<RSAKeyPair> findAllKeys();

    void save(RSAKeyPair rsaKeyPair);

}
