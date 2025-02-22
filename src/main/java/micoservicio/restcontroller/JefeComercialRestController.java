package micoservicio.restcontroller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import micoservicio.modelo.dto.PedidoDto;
import micoservicio.modelo.entities.Comercial;
import micoservicio.modelo.entities.Pedido;
import micoservicio.modelo.service.ComercialService;
import micoservicio.modelo.service.PedidoService;

@Tag(name = "Jefe Comercial", description = "Operaciones para gestionar comerciales y pedidos")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/jefecomercial")
public class JefeComercialRestController {

    @Autowired
    private ComercialService comercialService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ModelMapper mapper;

    
    //Documentación de pruebas en PostMan -> https://documenter.getpostman.com/view/42540393/2sAYdcqXGk
    
    //Documentación de Endpoints en SWAGGER -> http://localhost:8084/swagger-ui/index.html#/
    
    @Operation(summary = "Dar de alta un comercial", description = "Crea un nuevo comercial en la bbdd")
    @PostMapping("/alta")
    public ResponseEntity<Comercial> alta(@RequestBody Comercial comercial) {
        Comercial nuevo = comercialService.alta(comercial);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar un comercial", description = "Elimina el comercioal si es que no tiene pedidos asociados")
    @DeleteMapping("/eliminar/{idComercial}")
    public ResponseEntity<String> eliminar(@PathVariable int idComercial) {
        String mensaje = comercialService.eliminar(idComercial);
        if(mensaje.startsWith("No se puede")) {
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @Operation(summary = "Obtener un comercial", description = "Devuelve los datos del comercial cuyo ID coincide")
    @GetMapping("/uno/{idComercial}")
    public ResponseEntity<Comercial> uno(@PathVariable int idComercial) {
        Comercial comercial = comercialService.buscarUno(idComercial);
        if(comercial != null) {
            return new ResponseEntity<>(comercial, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Comerciales por cliente", description = "Devuelve la lista de comerciales que han atendido pedidos de un cliente")
    @GetMapping("/bycliente/{idCliente}")
    public ResponseEntity<List<Comercial>> byCliente(@PathVariable int idCliente) {
        List<Comercial> comerciales = comercialService.buscarPorCliente(idCliente);
        if(comerciales.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comerciales, HttpStatus.OK);
    }

    @Operation(summary = "Comerciales sin pedidos", description = "Devuelve la lista de comerciales que no han atendido ningún pedido")
    @GetMapping("/sinpedidos")
    public ResponseEntity<List<Comercial>> sinPedidos() {
        List<Comercial> comerciales = comercialService.comercialesSinPedidos();
        return new ResponseEntity<>(comerciales, HttpStatus.OK);
    }

    @Operation(summary = "Pedidos por comercial", description = "Devuelve la lista de pedidos gestionados por el comercial (usa PedidoDto)")
    @GetMapping("/pedidos/{idComercial}")
    public ResponseEntity<List<PedidoDto>> pedidosPorComercial(@PathVariable int idComercial) {
        List<Pedido> pedidos = pedidoService.buscarPorComercial(idComercial);
        List<PedidoDto> pedidosDto = pedidos.stream()
                                            .map(p -> mapper.map(p, PedidoDto.class))
                                            .collect(Collectors.toList());
        if(pedidosDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pedidosDto, HttpStatus.OK);
    }

    @Operation(summary = "Total de pedidos por comercial", description = "Obtiene la suma de los importes de los pedidos por comercial")
    @GetMapping("/totalpedidos")
    public ResponseEntity<Map<String, Double>> totalPedidos() {
        Map<String, Double> total = comercialService.totalPedidos();
        return new ResponseEntity<>(total, HttpStatus.OK);
    }
}
