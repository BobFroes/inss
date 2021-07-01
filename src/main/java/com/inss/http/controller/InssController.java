package com.inss.http.controller;

import com.inss.domain.Inss;
import com.inss.http.request.InssRequest;
import com.inss.http.response.InssResponse;
import com.inss.service.SaveService;
import com.inss.service.DeleteService;
import com.inss.service.RetrieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/inss")
public class InssController {

    private final SaveService saveService;
    private final RetrieveService retrieveService;
    private final DeleteService deleteService;

    @Autowired
    public InssController(SaveService saveService, RetrieveService retrieveService,
                          DeleteService deleteService) {
        this.saveService = saveService;
        this.retrieveService = retrieveService;
        this.deleteService = deleteService;
    }

    @PostMapping
    public ResponseEntity<InssResponse> create(@RequestBody @Valid InssRequest request) {
        Inss inss = InssRequest.from(request);
        InssResponse response = InssResponse.from(saveService.execute(inss));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InssResponse> retrieve(@PathVariable UUID id) {
        AtomicReference<ResponseEntity<InssResponse>> result = new AtomicReference<>();

        var optional = retrieveService.execute(id);

        optional.ifPresentOrElse(
                inss -> {
                    var response = InssResponse.from(inss);
                    result.set(ResponseEntity.ok(response));
                },
                () -> result.set(ResponseEntity.notFound().build())
        );

        return result.get();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        var optional = retrieveService.execute(id);

        if (optional.isEmpty()) { return ResponseEntity.notFound().build(); }

        deleteService.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<InssResponse> update(@PathVariable UUID id, @RequestBody @Valid InssRequest request) {
        var optional = retrieveService.execute(id);

        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(InssResponse.from(saveService.execute(InssRequest.from(optional.get().getId(), request))));
    }

}