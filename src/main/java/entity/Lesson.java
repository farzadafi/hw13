package entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(mappedBy = "lessons")
    private List<Student> students;

    @Column(nullable = false)
    private Integer idOfferLesson;
    private Integer quarterNumber;
    private Integer yearEducation;
    private Integer grade;


}
