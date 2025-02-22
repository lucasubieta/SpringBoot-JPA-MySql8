package micoservicio.modelo.service;

import java.util.List;
import java.util.Map;
import micoservicio.modelo.entities.Comercial;

public interface ComercialService {
    Comercial alta(Comercial comercial);
    String eliminar(int idComercial);
    Comercial buscarUno(int idComercial);
    List<Comercial> buscarPorCliente(int idCliente);
    List<Comercial> comercialesSinPedidos();
    Map<String, Double> totalPedidos();
}
