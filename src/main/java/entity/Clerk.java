package entity;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@DiscriminatorValue("Clerk")
public class Clerk extends UserAccount {
    private Integer salary;

    public Clerk(Integer id, String fullName, String nationalId, String password,Integer salary) {
        super(id, fullName, nationalId, password);
        this.salary = salary;
    }

}
