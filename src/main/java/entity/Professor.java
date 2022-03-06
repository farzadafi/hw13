package entity;

import entity.enomuration.KindProfessor;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@DiscriminatorValue("Professor")
public class Professor extends UserAccount {
    private KindProfessor kindProfessor;

    public Professor(Integer id, String fullName, String nationalId, String password, KindProfessor kindProfessor) {
        super(id, fullName, nationalId, password);
        this.kindProfessor = kindProfessor;
    }
}
