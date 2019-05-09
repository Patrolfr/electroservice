package komo.fraczek.electronicsservice.domain.dto;


import komo.fraczek.electronicsservice.domain.Equipment;
import komo.fraczek.electronicsservice.domain.Parameter;
import komo.fraczek.electronicsservice.domain.ServiceStatus;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EquipmentResponse {

    private String name;

    private String category;

    private String serviceCode;

    private HashMap<String, String> parameters;

    private List<String> comments;

    private ServiceStatus serviceStatus;


    public static EquipmentResponse wrapEquipment(final Equipment equipment){
        EquipmentResponse equipmentWrapper = new EquipmentResponse();
        equipmentWrapper.name = equipment.getName();
        equipmentWrapper.category = equipment.getCategory().getName();
        equipmentWrapper.serviceStatus = equipment.getServiceStatus();
        equipmentWrapper.comments = equipment.getComments();
        equipmentWrapper.parameters = (HashMap<String,String>) equipment.getParameters().stream().collect(Collectors.toMap(Parameter::getKey,Parameter::getValue));
        equipmentWrapper.serviceCode = equipment.getServiceCode();
        return equipmentWrapper;
    }
}