package mx.arkn37.meli.service;

import mx.arkn37.meli.dto.StatusResponse;
import mx.arkn37.meli.model.Status;
import mx.arkn37.meli.dto.CreateStatusRequest;
import mx.arkn37.meli.dto.UpdateStatusRequest;
import java.util.List;

public interface StatusService {
    List<StatusResponse> findAll();

    Status findById(Long id);

    Status findByCode(String code);

    StatusResponse createStatus(CreateStatusRequest request);

    StatusResponse updateStatus(Long id, UpdateStatusRequest request);

    void deleteStatus(Long id);
}
