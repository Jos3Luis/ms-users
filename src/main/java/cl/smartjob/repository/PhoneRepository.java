package cl.smartjob.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import cl.smartjob.entity.Phone;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PhoneRepository extends ReactiveCrudRepository<Phone, UUID> {
     
    
    @Query("INSERT INTO phones (id ,number ,citycode ,countrycode ,user_id) VALUES (:id ,:number ,:citycode ,:countrycode ,:user_id)")
    Mono<Phone> insert(@Param("id") UUID id,
                      @Param("number") String number,
                      @Param("citycode") String citycode,
                      @Param("countrycode") String countrycode,
                      @Param("user_id") UUID user_id);

	Flux<Phone> findByUserId(UUID userId);
}

