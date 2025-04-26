package cl.smartjob.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("users")
public class User {

    @Id
    private UUID id;

    private String name;
    private String email;
    private String password;

    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;

    private String token;

    private boolean isActive;
    
    @Transient
    private List<Phone> phones;

    
}
