package komo.fraczek.electronicsservice.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PublicController {

    @RequestMapping("/publiccontroller")
    @ResponseStatus(value = HttpStatus.CHECKPOINT)
    public String publicEndopoint(){
        return "notSecuredTestEndpoint";
    }
}