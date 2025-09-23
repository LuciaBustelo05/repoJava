package entidades;

import repository.InMemoryRepository;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.*;
import lombok.Builder;
public class Main {
    public static void main(String[] args) {
        InMemoryRepository<Empresa> empresasInMemoryRepository = new InMemoryRepository<>();

        Pais argentina = Pais.builder()
                .nombre("argenitna").build();
        Provincia buenosAires = Provincia.builder()
                .id(1L)
                .nombre("buenos aires")
                .pais(argentina)
                .build();
        Localidad caba = Localidad.builder()
                .id(1l)
                .nombre("caba")
                .provincia(buenosAires)
                .build();
        Domicilio hermanaisidra = Domicilio.builder()
                .id(1L)
                .calle("hermana Isidra")
                .numero(59788)
                .localidad(caba)
                .cp(3654)
                .build();
        Localidad laPata = Localidad.builder()
                .id(2L)
                .nombre("La plata")
                .provincia(buenosAires)
                .build();
        Domicilio avenidaSanRafael = Domicilio.builder()
                .id(2L)
                .cp(7893)
                .localidad(laPata)
                .calle("avenida san rafael")
                .numero(897)
                .build();
// CORDOBA LOCALIDADES Y DOMICILIOS
        Provincia cordoba = Provincia.builder()
                .id(2L)
                .pais(argentina)
                .nombre("cordoba")
                .build();
        Localidad capital = Localidad.builder()
                .id(3L)
                .provincia(cordoba)
                .nombre("capital")
                .build();
        Domicilio taboada = Domicilio.builder()
                .id(3L)
                .numero(1963)
                .calle("taboada")
                .cp(7899)
                .localidad(capital)
                .build();
        Localidad villaCarlosPaz = Localidad.builder()
                .id(4L)
                .nombre("villa carlos paz")
                .provincia(cordoba)
                .build();
        Domicilio peru = Domicilio.builder()
                .id(4L)
                .localidad(villaCarlosPaz)
                .calle("peru")
                .numero(755)
                .cp(893)
                .build();
        //SUCURSALES
        Sucursal s1 = Sucursal.builder()
                .id(1L)
                .nombre("sucursal 1 vea")
                .horarioApertura(LocalTime.of(9, 0))
                .horarioCierre(LocalTime.of(15, 0))
                .en_casa_Matriz(false)
                .domicilio(hermanaisidra)
                .build();
        Sucursal s2 = Sucursal.builder()
                .id(2L)
                .nombre("sucursal 2 carreofur")
                .horarioCierre(LocalTime.of(16, 0))
                .horarioApertura(LocalTime.of(20, 15))
                .domicilio(avenidaSanRafael)
                .en_casa_Matriz(true)
                .build();
        Sucursal s3 = Sucursal.builder()
                .id(3L)
                .nombre("sucursal 3 maxi")
                .en_casa_Matriz(false)
                .horarioApertura(LocalTime.of(15, 30))
                .horarioCierre(LocalTime.of(18, 15))
                .domicilio(taboada)
                .build();
        Sucursal s4 = Sucursal.builder()
                .id(4L)
                .nombre("sucursal 4 libertad ")
                .domicilio(peru)
                .horarioCierre(LocalTime.of(19, 10))
                .horarioApertura(LocalTime.of(9, 0))
                .en_casa_Matriz(false)
                .build();
        //CREAR EMPRESAS y asociarlas a SUCURSALES
        Empresa e1 = Empresa.builder()
                .nombre("empresa 1 nicolas")
                .id(1L)
                .cuit(123456)
                .logo("flor")
                .razonSocial("osep")
                .sucursales(new HashSet<>(Set.of(s1, s2)))
                .build();
        Empresa e2 = Empresa.builder()
                .nombre("empresa 2 exequiel")
                .id(2L)
                .cuit(1456329)
                .logo("estrella")
                .razonSocial("osep")
                .sucursales(new HashSet<>(Set.of(s3, s4)))
                .build();
//ASIGNAR EMPRESAS a SUCURSALES
        s1.setEmpresa(e1);
        s2.setEmpresa(e1);
        s3.setEmpresa(e2);
        s4.setEmpresa(e2);
// GUARDAR empresas en el repositorio
        empresasInMemoryRepository.save(e1);
        empresasInMemoryRepository.save(e2);
//MOSTrar todas las empress con el "findAll"
        System.out.println("Todas las empresas");
        List<Empresa> todasLasEmpresas = empresasInMemoryRepository.findAll();
//BUSCAR empresa por ID
        System.out.println("\nBUSCANDO EMPRESA POR ID: 1");
        Optional<Empresa> empresaBuscadaPorID = empresasInMemoryRepository.findById(1L);
        System.out.println(empresaBuscadaPorID);
//BUSCAR EMPRESA POR NOMBRE
        System.out.println("\n BUSCAR EMPRESA POR NOMBRE");
        List<Empresa> empresaBuscadaPorNombre = empresasInMemoryRepository.genericFindByField("nombre", "Empresa 2 bianca");
        System.out.println(empresaBuscadaPorNombre);
//ACTUALIZAR DATOS DE EMPRESAS ID : 1
        System.out.println("\n ACTUALIZAR DATOS DE EMPRESAS ID : 1");
        Optional<Empresa> empresaActualizada = empresasInMemoryRepository.genericUpdate(1L, e1.builder()
                .nombre("empresa 1 nicolas")
                .id(1L)
                .cuit(123456)
                .logo("flor")
                .razonSocial("osep")
                .sucursales(e1.getSucursales())
                .build());
        Optional<Empresa> empresaBuscada2 = empresasInMemoryRepository.findById(1L);
        System.out.println(empresaBuscada2);
//ELIMINAR EMPRESA POR ID
        System.out.println("\nELIMINAR EMPRESA POR ID: 1");
        empresasInMemoryRepository.genericDelete(1L);
        if (empresasInMemoryRepository.findById(1L).isEmpty()) {
            System.out.println("Empresa eliminada correctamente");
        }

        System.out.println("\nEmpresas después de la eliminación:");
        List<Empresa> empresasRestantes = empresasInMemoryRepository.findAll();
        empresasRestantes.forEach(System.out::println);


    }
}
