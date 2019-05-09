package komo.fraczek.electronicsservice.domain.dto;


import komo.fraczek.electronicsservice.domain.ServiceStatus;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Getter
@AllArgsConstructor
@Builder
@ToString
public class EquipmentPayload {
    @NotBlank(message = "Name should not be empty.")
    private String name;

    @NotBlank(message = "Category should not be empty.")
    private String category;

    private HashMap<String, String> parameters;

    private List<String> comments;

    @Enumerated(EnumType.STRING)
    private ServiceStatus serviceStatus;
}
