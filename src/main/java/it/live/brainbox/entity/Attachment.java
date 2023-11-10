package it.live.brainbox.entity;


import it.live.brainbox.entity.temp.AbsUUIDEntity;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Attachment extends AbsUUIDEntity {
    private String fileOriginalName;
    private long size;
    private String contentType;
    private String name;
}
