package it.live.brainbox.entity;

import it.live.brainbox.entity.temp.AbsUUIDEntity;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SubtitleWord extends AbsUUIDEntity {
    private String value;
    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Video video;
    private Integer count;
}
