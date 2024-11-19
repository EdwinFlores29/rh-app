package eoflores.rh_app.controlador;

import eoflores.rh_app.excepcion.RecursoNoEncontradoExcepcion;
import eoflores.rh_app.modelo.Empleado;
import eoflores.rh_app.servicio.IEmpleadoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rh-app")
//htpp://localhost:8080/rh-app/empleados-buscar
@CrossOrigin(value = "http://localhost:3000")
public class EmpleadoControlador {
    private static final Logger looger =
            LoggerFactory.getLogger(EmpleadoControlador.class);

    @Autowired
    private IEmpleadoServicio empleadoServicio;

    //http://localhost:8080/rha-pp/empleados
    @GetMapping("/empleados")
    public List<Empleado> Obtenerempleados(){
        var empleados = empleadoServicio.ListarEmpleados();
        empleados.forEach((empleado -> looger.info(empleado.toString())));
        return empleados;
    }

    @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado){
        looger.info("Empleado a agregar:" + empleado);
        return empleadoServicio.GuardarEmpleado(empleado);
    }

    @GetMapping ("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleado(@PathVariable Integer id){
        Empleado empleado = empleadoServicio.BuscarEmpleadoPorId(id);
        if(empleado == null)
            throw new RecursoNoEncontradoExcepcion(("No se encontro el empleado con el id: " + id));
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@RequestBody Empleado empleadoRecibido, @PathVariable Integer id){
        Empleado empleado = empleadoServicio.BuscarEmpleadoPorId(id);
        if (empleado == null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el empleado con el id: " + id);
        empleado.setNombre(empleadoRecibido.getNombre());
        empleado.setDepartamento(empleadoRecibido.getDepartamento());
        empleado.setSueldo(empleadoRecibido.getSueldo());
        empleadoServicio.GuardarEmpleado(empleado);
        return ResponseEntity.ok(empleado);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>>
    eliminarEmpleado(@PathVariable Integer id){
        Empleado empleado = empleadoServicio.BuscarEmpleadoPorId(id);
        if (empleado == null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el empleado con el id: " + id);
        empleadoServicio.eliminarEmpleado(empleado);
        //JSON respuesta {"eliminado": "true"}
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
