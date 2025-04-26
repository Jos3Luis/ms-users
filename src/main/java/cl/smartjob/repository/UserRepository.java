package cl.smartjob.repository;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import cl.smartjob.entity.User;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, UUID> {
    Mono<User> findByEmail(String email);
    Mono<User> findByEmailAndPassword(String email, String password);
    Mono<Boolean> existsByEmail(String email);
    
    @Query("INSERT INTO users (id, name, email, password, created, modified, token, is_active) VALUES (:id, :name, :email, :password, :created, :modified, :token, :isActive)")
    Mono<Void> insert(@Param("id") UUID id,
                      @Param("name") String name,
                      @Param("email") String email,
                      @Param("password") String password,
                      @Param("created") LocalDateTime created,
                      @Param("modified") LocalDateTime modified,
                      @Param("token") String token,
                      @Param("isActive") boolean isActive);
    
}