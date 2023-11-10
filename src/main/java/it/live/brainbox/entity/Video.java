package it.live.brainbox.entity;

import it.live.brainbox.entity.enums.Genre;
import it.live.brainbox.entity.temp.AbsLongEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Video extends AbsLongEntity {
    @Column(nullable = false)
    private String name;
    @OneToOne
    private Attachment avatar;
    private String description;
    private Integer price;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private Integer belongAge;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Language language;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL)
    private List<SubtitleWord> subtitleWords;
}
