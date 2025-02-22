package micoservicio.modelo.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of="idPedido")
@Builder
public class PedidoDto implements Serializable{
		
	private static final long serialVersionUID = 1L;
	
	private int idPedido;
	private double importe;
	private LocalDate fecha;
	private int idCliente;
	private int idComercial;

}
