package mx.arkn37.meli.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mx.arkn37.meli.dto.CreateStatusRequest;
import mx.arkn37.meli.dto.StatusResponse;
import mx.arkn37.meli.dto.UpdateStatusRequest;
import mx.arkn37.meli.service.StatusService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StatusController.class)
class StatusControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private StatusService statusService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @DisplayName("GET /status returns all statuses")
  void getAllStatus_returnsList() throws Exception {
    StatusResponse response = new StatusResponse();
    response.setId(1L);
    response.setName("Test");
    Mockito.when(statusService.findAll()).thenReturn(List.of(response));

    mockMvc.perform(get("/status"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(1L))
        .andExpect(jsonPath("$[0].name").value("Test"));
  }

  @Test
  @DisplayName("POST /status creates a new status")
  void createStatus_returnsCreated() throws Exception {
    CreateStatusRequest request = new CreateStatusRequest();
    request.setName("Test");
    request.setDescription("desc");
    request.setCode("CODE");
    StatusResponse response = new StatusResponse();
    response.setId(1L);
    response.setName("Test");
    Mockito.when(statusService.createStatus(any(CreateStatusRequest.class))).thenReturn(response);

    mockMvc.perform(post("/status")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.name").value("Test"));
  }

  @Test
  @DisplayName("PUT /status/{id} updates a status")
  void updateStatus_returnsOk() throws Exception {
    UpdateStatusRequest request = new UpdateStatusRequest();
    request.setName("Updated");
    StatusResponse response = new StatusResponse();
    response.setId(1L);
    response.setName("Updated");
    Mockito.when(statusService.updateStatus(eq(1L), any(UpdateStatusRequest.class))).thenReturn(response);

    mockMvc.perform(put("/status/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.name").value("Updated"));
  }

  @Test
  @DisplayName("DELETE /status/{id} deletes a status")
  void deleteStatus_returnsNoContent() throws Exception {
    Mockito.doNothing().when(statusService).deleteStatus(1L);
    mockMvc.perform(delete("/status/1"))
        .andExpect(status().isNoContent());
  }
}
