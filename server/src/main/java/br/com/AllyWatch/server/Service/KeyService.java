package br.com.AllyWatch.server.Service;

import br.com.AllyWatch.server.Domain.KeyCrypt;
import br.com.AllyWatch.server.Repository.KeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.*;
import java.util.Optional;

import static br.com.AllyWatch.server.Security.Cryptography.ALGORITHM;
import static br.com.AllyWatch.server.Security.Cryptography.KEY_SIZE;

@Service
public class KeyService {

    @Autowired
    private KeyRepository keyRepository;

    public KeyCrypt findKey() {
        Optional<KeyCrypt> findKey = keyRepository.findAll().stream().findFirst();
        KeyCrypt key = null;

        if (findKey.isEmpty()) {
            try {
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
                keyPairGenerator.initialize(KEY_SIZE);
                KeyPair keyPair = keyPairGenerator.generateKeyPair();
                PublicKey publicKey = keyPair.getPublic();
                PrivateKey privateKey = keyPair.getPrivate();
                key = KeyCrypt.builder().privateKey(privateKey).publicKey(publicKey).build();
                keyRepository.save(key);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else {
            key = findKey.get();
        }
        return key;
    }
}
