package eoflores.rh_app.servicio;

import eoflores.rh_app.modelo.Empleado;

import java.util.List;

public interface IEmpleadoServicio {
    public List<Empleado> ListarEmpleados();

    public Empleado BuscarEmpleadoPorId(Integer idEmpleado);

    public Empleado GuardarEmpleado(Empleado empleado);

    public void eliminarEmpleado(Empleado empleado);
}
