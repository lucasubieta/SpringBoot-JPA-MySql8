package micoservicio.restcontroller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import micoservicio.modelo.dto.PedidoDto;
import micoservicio.modelo.entities.Pedido;
import micoservicio.modelo.service.PedidoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pedidos")
public class PedidorestController {
	
	@Autowired
	private PedidoService pserv;
	
	@Autowired
	private ModelMapper maper;
	
	@GetMapping("/uno/{idPedido}")
	public ResponseEntity<Pedido> uno(@PathVariable int idPedido) {
		Pedido pedido = pserv.buscarUno(idPedido);
		System.out.println(pedido);
		PedidoDto pdto = new PedidoDto();
		if (pedido != null) {
	//		maper.map(pdto, pedido);
			pdto = maper.map(pedido, PedidoDto.class);
			System.out.println(pdto);
		}
		return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
	}
	

}
