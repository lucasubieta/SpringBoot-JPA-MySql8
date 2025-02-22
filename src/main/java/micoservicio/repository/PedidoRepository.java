package micoservicio.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import micoservicio.modelo.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    
    // Busca los pedidos gestionados por un comercial dado
    List<Pedido> findByComercialIdComercial(int idComercial);
    
    // Busca los pedidos realizados por un cliente dado
    List<Pedido> findByClienteIdCliente(int idCliente);
}
