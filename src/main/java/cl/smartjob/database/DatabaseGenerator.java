package cl.smartjob.database;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Component;

import cl.smartjob.entity.User;

//@Configuration
@Component
public class DatabaseGenerator implements ApplicationRunner {
	
	private final R2dbcEntityTemplate template;

    public DatabaseGenerator(R2dbcEntityTemplate template) {
        this.template = template;
    }

    @Override
    public void run(ApplicationArguments args) {
    	User usuario = User.builder()
            	.id(UUID.randomUUID())
                .name("Juan Rodriguez")
                .email("juan@rodriguez.org")
                .password("cazador")
                .created(LocalDateTime.now())
                .modified(null)
                .lastLogin(null)
                .token(UUID.randomUUID().toString())
                .isActive(true)
                .build();
    	

        template.select(User.class)
                .matching(Query.query(Criteria.where("id").is(usuario.getId())))
                .one()
                .flatMap(existingUser -> {
                    // Si el usuario existe, lo actualizamos
                    existingUser.setName("Nuevo Nombre");
                    return template.update(existingUser);
                })
                .switchIfEmpty(
                		
                    // Si no existe, lo insertamos
                    template.insert(usuario)
                )
                .doOnSuccess(result -> System.out.println("Usuario procesado exitosamente"))
                .doOnError(error -> System.err.println("Error al procesar usuario: " + error.getMessage()))
                .subscribe();
    }

 
}
