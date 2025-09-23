package entidades;

import lombok.*;

@AllArgsConstructor
@ToString
@Builder
@Data
@Setter
@Getter
@NoArgsConstructor

public class Provincia {
    private Long id;
    private String nombre;
    private Pais pais;
}
