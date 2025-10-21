package mx.arkn37.meli.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mx.arkn37.meli.model.Status;
import mx.arkn37.meli.service.StatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("status")
@RequiredArgsConstructor
@Tag(name = "Status Management", description = "APIs for creating, reading, updating, and deleting statuses")
public class StatusController {

    private final StatusService statusService;

    @GetMapping
    @Operation(summary = "Get all statuses", description = "Retrieves a list of all statuses.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "404", description = "No statuses found")
    })
    public ResponseEntity<List<Status>> getAllStatus() {
        List<Status> statuses = statusService.findAll();
        return ResponseEntity.ok(statuses);
    }
}
