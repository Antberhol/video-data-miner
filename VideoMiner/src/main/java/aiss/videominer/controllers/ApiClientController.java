package aiss.videominer.controllers;

import aiss.videominer.model.ApiClient;
import aiss.videominer.repository.ApiClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
@RequestMapping("/videominer/register")
public class ApiClientController {

    private final ApiClientRepository apiClientRepository;


    @Autowired
    public ApiClientController(ApiClientRepository apiClientRepository){
        this.apiClientRepository = apiClientRepository;
    }

    @RequestMapping(value = "/{serviceName}", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<ApiClient> registerClient(@PathVariable String serviceName) {
        try {
            Optional<ApiClient> existingClient = apiClientRepository.findByName(serviceName);
            if (existingClient.isPresent()) {
                return ResponseEntity.ok(existingClient.get());
            }

            ApiClient newClient = new ApiClient(serviceName);
            ApiClient savedClient = apiClientRepository.save(newClient);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
