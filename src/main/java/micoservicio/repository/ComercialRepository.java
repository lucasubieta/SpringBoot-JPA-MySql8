package micoservicio.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import micoservicio.modelo.entities.Comercial;

public interface ComercialRepository extends JpaRepository<Comercial, Integer> {
    
    @Query("select c from Comercial c where c.idComercial not in (select p.comercial.idComercial from Pedido p)")
    List<Comercial> findComercialesSinPedidos();
}
