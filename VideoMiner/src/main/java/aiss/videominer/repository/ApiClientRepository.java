package aiss.videominer.repository;

import aiss.videominer.model.ApiClient;
import aiss.videominer.model.Caption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApiClientRepository extends JpaRepository<ApiClient, String> {

    Optional<ApiClient> findByToken(String token);
    Optional<ApiClient> findByName(String name);
}