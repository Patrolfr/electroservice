package komo.fraczek.electronicsservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parameters")
@Setter
@NoArgsConstructor
@Getter
public class Parameter {

    @Id
    @GeneratedValue
    private Long id;

    private String key;

    private String value;

    public Parameter(String key, String value){
        this.setKey(key);
        this.setValue(value);
    }
}