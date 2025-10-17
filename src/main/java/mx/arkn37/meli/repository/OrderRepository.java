package mx.arkn37.meli.repository;

import mx.arkn37.meli.model.Order;
import mx.arkn37.meli.model.Status;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends ExposedRepository<Order>  {

}
