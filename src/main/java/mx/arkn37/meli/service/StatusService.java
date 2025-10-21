package mx.arkn37.meli.service;

import mx.arkn37.meli.model.Status;

import java.util.List;

public interface StatusService {
    List<Status> findAll();
    Status findById(Long id);
    Status findByCode(String code);
}
