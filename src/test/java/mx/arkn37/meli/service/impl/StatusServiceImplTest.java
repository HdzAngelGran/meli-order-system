package mx.arkn37.meli.service.impl;

import mx.arkn37.meli.dto.CreateStatusRequest;
import mx.arkn37.meli.dto.StatusResponse;
import mx.arkn37.meli.dto.UpdateStatusRequest;
import mx.arkn37.meli.model.Status;
import mx.arkn37.meli.repository.StatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StatusServiceImplTest {
  @Mock
  private StatusRepository statusRepository;

  @InjectMocks
  private StatusServiceImpl statusService;

  private final UUID currentUser = UUID.fromString("ed943e94-4b8c-40c8-be20-0da27027fb89");

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void createStatus_shouldSaveAndReturnResponse() {
    CreateStatusRequest request = new CreateStatusRequest();
    request.setName("Test");
    request.setDescription("desc");
    request.setCode("CODE");
    Status saved = new Status();
    saved.setId(1L);
    saved.setName("Test");
    saved.setDescription("desc");
    saved.setCode("CODE");
    saved.setEnabled(true);
    saved.setCreatedAt(LocalDateTime.now());
    saved.setCreatedBy(currentUser);
    when(statusRepository.save(any(Status.class))).thenReturn(saved);

    StatusResponse response = statusService.createStatus(request);
    assertThat(response.getName()).isEqualTo("Test");
    assertThat(response.getCode()).isEqualTo("CODE");
    verify(statusRepository).save(any(Status.class));
  }

  @Test
  void updateStatus_shouldUpdateAndReturnResponse() {
    Status status = new Status();
    status.setId(1L);
    status.setName("Old");
    status.setDescription("OldDesc");
    status.setCode("OLD");
    status.setEnabled(true);
    when(statusRepository.findActiveById(1L)).thenReturn(Optional.of(status));
    when(statusRepository.save(any(Status.class))).thenReturn(status);

    UpdateStatusRequest request = new UpdateStatusRequest();
    request.setName("New");
    request.setDescription("NewDesc");
    request.setCode("NEW");
    StatusResponse response = statusService.updateStatus(1L, request);
    assertThat(response.getName()).isEqualTo("New");
    assertThat(response.getCode()).isEqualTo("NEW");
    verify(statusRepository, atLeastOnce()).save(any(Status.class));
  }

  @Test
  void deleteStatus_shouldDisableStatus() {
    Status status = new Status();
    status.setId(1L);
    status.setEnabled(true);
    when(statusRepository.findActiveById(1L)).thenReturn(Optional.of(status));
    when(statusRepository.save(any(Status.class))).thenReturn(status);

    statusService.deleteStatus(1L);
    assertThat(status.isEnabled()).isFalse();
    verify(statusRepository).save(status);
  }

  @Test
  void findAll_shouldReturnMappedResponses() {
    Status status = new Status();
    status.setId(1L);
    status.setName("Test");
    status.setCode("CODE");
    status.setEnabled(true);
    when(statusRepository.findAllActive()).thenReturn(List.of(status));

    List<StatusResponse> responses = statusService.findAll();
    assertThat(responses).hasSize(1);
    assertThat(responses.get(0).getName()).isEqualTo("Test");
  }

  @Test
  void findById_shouldReturnStatus() {
    Status status = new Status();
    status.setId(1L);
    status.setName("Test");
    status.setEnabled(true);
    when(statusRepository.findActiveById(1L)).thenReturn(Optional.of(status));

    Status found = statusService.findById(1L);
    assertThat(found).isSameAs(status);
  }

  @Test
  void findById_shouldThrowIfNotFound() {
    when(statusRepository.findActiveById(1L)).thenReturn(Optional.empty());
    assertThatThrownBy(() -> statusService.findById(1L))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Invalid status for order");
  }

  @Test
  void findById_shouldThrowIfNotEnabled() {
    Status status = new Status();
    status.setId(1L);
    status.setName("Test");
    status.setEnabled(false);
    when(statusRepository.findActiveById(1L)).thenReturn(Optional.of(status));
    assertThatThrownBy(() -> statusService.findById(1L))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("is not active");
  }

  @Test
  void findByCode_shouldReturnStatus() {
    Status status = new Status();
    status.setId(1L);
    status.setName("Test");
    status.setCode("CODE");
    status.setEnabled(true);
    when(statusRepository.findActiveByCode("CODE")).thenReturn(Optional.of(status));
    Status found = statusService.findByCode("CODE");
    assertThat(found).isSameAs(status);
  }

  @Test
  void findByCode_shouldThrowIfNotFound() {
    when(statusRepository.findActiveByCode("CODE")).thenReturn(Optional.empty());
    assertThatThrownBy(() -> statusService.findByCode("CODE"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Invalid status for order");
  }

  @Test
  void findByCode_shouldThrowIfNotEnabled() {
    Status status = new Status();
    status.setId(1L);
    status.setName("Test");
    status.setCode("CODE");
    status.setEnabled(false);
    when(statusRepository.findActiveByCode("CODE")).thenReturn(Optional.of(status));
    assertThatThrownBy(() -> statusService.findByCode("CODE"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("is not active");
  }
}
