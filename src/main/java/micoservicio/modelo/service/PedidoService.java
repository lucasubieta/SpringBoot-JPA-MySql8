package micoservicio.modelo.service;

import java.util.List;
import micoservicio.modelo.entities.Pedido;

public interface PedidoService {
    Pedido buscarUno(int idPedido);
    List<Pedido> buscarPorComercial(int idComercial);
}
