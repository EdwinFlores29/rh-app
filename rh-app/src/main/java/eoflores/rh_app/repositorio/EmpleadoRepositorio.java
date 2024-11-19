package eoflores.rh_app.repositorio;

import eoflores.rh_app.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepositorio extends JpaRepository<Empleado, Integer> {

}