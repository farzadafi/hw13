package entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("Student")
public class Student extends UserAccount {

    @ManyToMany(mappedBy = "students")
    private Set<Lesson> lessons;

    public Student(Integer id, String fullName, String nationalId, String password) {
        super(id, fullName, nationalId, password);
    }
}
