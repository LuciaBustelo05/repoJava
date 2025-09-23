package entidades;

import lombok.*;
import java.time.LocalTime;
@AllArgsConstructor
@ToString
@Data
@Builder
@Setter
@Getter
@NoArgsConstructor

public class Sucursal {
    private Long id;
    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private boolean en_casa_Matriz;
    private Empresa empresa;
    private Domicilio domicilio;
}
