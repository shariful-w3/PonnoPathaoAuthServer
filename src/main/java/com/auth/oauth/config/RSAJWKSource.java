package com.auth.oauth.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.auth.oauth.repository.RSAKeyRepository;
import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Component
public class RSAJWKSource implements JWKSource<SecurityContext> {

    private final RSAKeyRepository rsaKeyRepository;

    public RSAJWKSource(RSAKeyRepository rsaKeyRepository) {
        this.rsaKeyRepository = rsaKeyRepository;
    }

    @Override
    public List<JWK> get(JWKSelector jwkSelector, SecurityContext securityContext) throws KeySourceException {
        var keys = rsaKeyRepository.findAllKeys();
        System.out.println("keys" + keys.toString());
        return keys.stream().map(key -> new RSAKey.Builder(key.rsaPublicKey())
                        .privateKey(key.rsaPrivateKey())
                        .keyID(key.id())
                        .build())
                .collect(Collectors.toList());
    }

}
