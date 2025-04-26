package cl.smartjob.entity;
 
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("phones")
public class Phone {

    @Id
    private UUID id;

    private String number;
    private String citycode;
    private String countrycode;

    @Column("user_id")
    private UUID userId;
}