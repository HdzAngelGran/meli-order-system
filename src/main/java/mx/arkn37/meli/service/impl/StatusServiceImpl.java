package mx.arkn37.meli.service.impl;

import lombok.RequiredArgsConstructor;
import mx.arkn37.meli.model.Status;
import mx.arkn37.meli.repository.StatusRepository;
import mx.arkn37.meli.service.StatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
  private final StatusRepository statusRepository;

  @Override
  public List<Status> findAll() {
      return statusRepository.findAllActive();
  }

  @Override
  public Status findById(Long id) throws IllegalArgumentException {
    Status status =  statusRepository.findActiveById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid status for order"));

      if (!status.isEnabled())
          throw new IllegalArgumentException("Status " + status.getName() + " is not active");

      return status;
  }

  @Override
  public Status findByCode(String code) throws IllegalArgumentException {
    Status status =  statusRepository.findActiveByCode(code)
        .orElseThrow(() -> new IllegalArgumentException("Invalid status for order"));

      if (!status.isEnabled())
          throw new IllegalArgumentException("Status " + status.getName() + " is not active");

      return status;
  }
}
