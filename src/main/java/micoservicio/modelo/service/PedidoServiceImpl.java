package micoservicio.modelo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import micoservicio.modelo.entities.Pedido;
import micoservicio.repository.PedidoRepository;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository prepo;

    @Override
    public Pedido buscarUno(int idPedido) {
        return prepo.findById(idPedido).orElse(null);
    }
    
    @Override
    public List<Pedido> buscarPorComercial(int idComercial) {
        return prepo.findByComercialIdComercial(idComercial);
    }
}
