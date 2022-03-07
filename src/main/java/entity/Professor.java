package entity;

import entity.enomuration.KindProfessor;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@DiscriminatorValue("Professor")
public class Professor extends UserAccount {
    @Enumerated(EnumType.STRING)
    private KindProfessor kindProfessor;

    public Professor(Integer id, String fullName, String nationalId, String password, KindProfessor kindProfessor) {
        super(id, fullName, nationalId, password);
        this.kindProfessor = kindProfessor;
    }
}
