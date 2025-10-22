package mx.arkn37.meli.service.impl;

import lombok.RequiredArgsConstructor;
import mx.arkn37.meli.dto.StatusResponse;
import mx.arkn37.meli.mappers.StatusMapper;
import mx.arkn37.meli.model.Status;
import mx.arkn37.meli.dto.CreateStatusRequest;
import mx.arkn37.meli.dto.UpdateStatusRequest;
import mx.arkn37.meli.repository.StatusRepository;
import mx.arkn37.meli.service.StatusService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final UUID currentUser = UUID.fromString("ed943e94-4b8c-40c8-be20-0da27027fb89");
    private final StatusRepository statusRepository;

    @Override
    public StatusResponse createStatus(CreateStatusRequest request) {
        Status status = new Status();
        status.setName(request.getName());
        status.setDescription(request.getDescription());
        status.setCode(request.getCode());
        status.setEnabled(true);
        status.setCreatedAt(LocalDateTime.now());
        status.setCreatedBy(currentUser);

        Status statusCreated = statusRepository.save(status);
        return StatusMapper.toResponse(statusCreated);
    }

    @Override
    public StatusResponse updateStatus(Long id, UpdateStatusRequest request) {
        Status status = findById(id);
        if (request.getName() != null)
            status.setName(request.getName());
        if (request.getDescription() != null)
            status.setDescription(request.getDescription());
        if (request.getCode() != null)
            status.setCode(request.getCode());

        status.setUpdatedAt(LocalDateTime.now());
        status.setUpdatedBy(currentUser);

        Status statusUpdated = statusRepository.save(status);
        return StatusMapper.toResponse(statusRepository.save(statusUpdated));
    }

    @Override
    public void deleteStatus(Long id) {
        Status status = findById(id);
        status.setEnabled(false);
        status.setDeletedAt(LocalDateTime.now());
        status.setUpdatedBy(currentUser);

        statusRepository.save(status);
    }


    @Override
    public List<StatusResponse> findAll() {
        return statusRepository.findAllActive()
                .stream().map(StatusMapper::toResponse)
                .toList();
    }

    @Override
    public Status findById(Long id) throws IllegalArgumentException {
        Status status = statusRepository.findActiveById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid status for order"));

        if (!status.isEnabled())
            throw new IllegalArgumentException("Status " + status.getName() + " is not active");

        return status;
    }

    @Override
    public Status findByCode(String code) throws IllegalArgumentException {
        Status status = statusRepository.findActiveByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Invalid status for order"));

        if (!status.isEnabled())
            throw new IllegalArgumentException("Status " + status.getName() + " is not active");

        return status;
    }
}
