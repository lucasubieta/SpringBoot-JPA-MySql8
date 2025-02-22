package micoservicio.configuracion;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import micoservicio.modelo.dto.PedidoDto;
import micoservicio.modelo.entities.Pedido;

@Configuration
public class PedidosConfiguration {
	
	@Bean
	ModelMapper getModelMapper() {
		return  new ModelMapper();
		
		
	 
		
	}

}
