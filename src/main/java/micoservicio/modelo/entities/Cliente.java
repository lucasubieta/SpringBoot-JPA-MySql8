package micoservicio.modelo.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of="idCliente")
@Builder
@Entity
@Table(name="clientes")
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cliente")
    private int idCliente;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String ciudad;
    private Integer categoria;  // Cambiado a Integer para evitar 500 en POSTMAN
}
