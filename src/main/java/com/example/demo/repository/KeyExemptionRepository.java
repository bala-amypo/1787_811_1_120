// src/main/java/com/example/demo/repository/KeyExemptionRepository.java
package com.example.demo.repository;

import com.example.demo.entity.KeyExemption;
import java.util.Optional;

public interface KeyExemptionRepository {
    KeyExemption save(KeyExemption e);
    Optional<KeyExemption> findByApiKey_Id(Long keyId);
}
