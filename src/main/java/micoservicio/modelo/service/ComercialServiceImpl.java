package micoservicio.modelo.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import micoservicio.modelo.entities.Comercial;
import micoservicio.modelo.entities.Pedido;
import micoservicio.repository.ComercialRepository;
import micoservicio.repository.PedidoRepository;

@Service
public class ComercialServiceImpl implements ComercialService {

    @Autowired
    private ComercialRepository crepo;

    @Autowired
    private PedidoRepository prepo;

    @Override
    public Comercial alta(Comercial comercial) {
        return crepo.save(comercial);
    }

    @Override
    public String eliminar(int idComercial) {
        // Comprobar si el comercial tiene pedidos asociados
        List<Pedido> pedidos = prepo.findByComercialIdComercial(idComercial);
        if (pedidos != null && !pedidos.isEmpty()) {
            return "No se puede eliminar el comercial con id " + idComercial + " porque tiene pedidos asociados.";
        } else {
            crepo.deleteById(idComercial);
            return "Comercial eliminado correctamente.";
        }
    }

    @Override
    public Comercial buscarUno(int idComercial) {
        return crepo.findById(idComercial).orElse(null);
    }

    @Override
    public List<Comercial> buscarPorCliente(int idCliente) {
        // Obtenemos los pedidos del cliente y extraemos los comerciales (sin repetir)
        List<Pedido> pedidos = prepo.findByClienteIdCliente(idCliente);
        return pedidos.stream()
                      .map(Pedido::getComercial)
                      .distinct()
                      .collect(Collectors.toList());
    }

    @Override
    public List<Comercial> comercialesSinPedidos() {
        return crepo.findComercialesSinPedidos();
    }

    @Override
    public Map<String, Double> totalPedidos() {
        List<Pedido> pedidos = prepo.findAll();
        // Agrupamos por nombre y apellido del comercial y sumamos los importes
        return pedidos.stream()
                      .collect(Collectors.groupingBy(
                        p -> p.getComercial().getNombre() + " " + p.getComercial().getApellido1(),
                        Collectors.summingDouble(Pedido::getImporte)
                      ));
    }
}
