package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.TacoOrder;
import tacos.Users;

import java.awt.print.Pageable;
import java.util.List;

public interface OrderRepository 
         extends CrudRepository<TacoOrder, Long> {
 //   List<TacoOrder> findByUserOrderByPlacedAtDesc (Pageable pageable, Users user);
}
