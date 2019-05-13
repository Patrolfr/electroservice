package komo.fraczek.electronicsservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import komo.fraczek.electronicsservice.domain.dto.EquipmentPayload;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "equipments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Equipment {

    @Id
    @GeneratedValue
    private Long Id;

    private String name;

    @ManyToOne()
    @JsonProperty
    private Category category;

    @Column(name = "service_code")
    private String serviceCode;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Parameter> parameters;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="comments", joinColumns=@JoinColumn(name="equipment_id"))
    @Embedded
    @Column(name="comments")
    private List<String> comments;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_status")
    private ServiceStatus serviceStatus;


    public static Equipment fromPayloadAndCategory(EquipmentPayload payload, Category category){
        return Equipment.builder().category(category)
                            .name(payload.getName())
                .comments(payload.getComments())
                .serviceStatus(payload.getServiceStatus())
                .parameters(payload.getParameters().entrySet().stream().map(entry -> new Parameter(entry.getKey(), entry.getValue())).collect(Collectors.toList()))
                .serviceStatus(payload.getServiceStatus() == null ? ServiceStatus.UNKNOWN : payload.getServiceStatus()).build();
    }

    public void changeStatus(ServiceStatus serviceStatus){
        this.serviceStatus=serviceStatus;
    }

    public void addComments(List<String> newComments){
        comments.addAll(newComments);
    }
}
