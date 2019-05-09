package komo.fraczek.electronicsservice.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CodeNotFoundException extends RuntimeException {

    @Getter
    private String code;

}
