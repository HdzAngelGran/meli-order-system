package mx.arkn37.meli.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mx.arkn37.meli.dto.CreateStatusRequest;
import mx.arkn37.meli.dto.UpdateStatusRequest;
import mx.arkn37.meli.dto.StatusResponse;
import mx.arkn37.meli.mappers.StatusMapper;
import mx.arkn37.meli.service.StatusService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<List<StatusResponse>> getAllStatus() {
        List<StatusResponse> responses = statusService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    @Operation(summary = "Create a new status", description = "Creates a new status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Status created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<StatusResponse> createStatus(@RequestBody CreateStatusRequest request) {
        var status = statusService.createStatus(request);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update a status", description = "Updates an existing status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status updated successfully"),
            @ApiResponse(responseCode = "404", description = "Status not found")
    })
    public ResponseEntity<StatusResponse> updateStatus(@PathVariable Long id,
                                                       @RequestBody UpdateStatusRequest request) {
        var status = statusService.updateStatus(id, request);
        return ResponseEntity.ok(status);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete a status", description = "Deletes an existing status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Status deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Status not found")
    })
    public ResponseEntity<Void> deleteStatus(@PathVariable Long id) {
        statusService.deleteStatus(id);
        return ResponseEntity.noContent().build();
    }
}
