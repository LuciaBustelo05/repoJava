package entidades;

import lombok.*;
import java.util.HashSet;
import java.util.Set;
@Getter
@ToString(exclude = "sucursales")
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Empresa {
    private Long id;
    private String nombre;
    private String razonSocial;
    private Integer cuit;
    private String logo;
    @Builder.Default
    private Set<Sucursal> sucursales = new HashSet<>();

}
