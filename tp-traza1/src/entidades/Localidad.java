package entidades;

import lombok.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data

public class Localidad {
    private Long id;
    private String nombre;
    private Domicilio domicilio;
    private Provincia provincia;

}
