package entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@DiscriminatorValue("Student")
public class Student extends UserAccount {

    @ManyToMany
    @JoinTable(
            name = "Student_Lessons",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id")
    )
    private Set<Lesson> lessons;

    public Student(Integer id, String fullName, String nationalId, String password) {
        super(id, fullName, nationalId, password);
    }
}
