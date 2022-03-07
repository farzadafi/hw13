package entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class OfferLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,unique = true)
    private String LessonName;

    @Column(nullable = false)
    private Integer unitNumber;

    @ManyToOne
    private Professor professor;

    public String CustomToString() {
        return "OfferLesson{" +
                "id=" + id +
                ", LessonName='" + LessonName + '\'' +
                ", unitNumber=" + unitNumber +
                '}';
    }
}
