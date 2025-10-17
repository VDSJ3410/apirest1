package mx.com.inube.apirest1.controller;

import mx.com.inube.apirest1.model.*;
import mx.com.inube.apirest1.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MvcController {

    private final EmpresaService empresaService;
    private final DepartamentoService departamentoService;
    private final EmpleadoService empleadoService;
    private final PuestosService puestoService;
    private final SucursalesService sucursalService;
    private final ReporteService reporteService;

    public MvcController(EmpresaService empresaService, DepartamentoService departamentoService,
                         EmpleadoService empleadoService, PuestosService puestoService,
                         SucursalesService sucursalService, ReporteService reporteService) {
        this.empresaService = empresaService;
        this.departamentoService = departamentoService;
        this.empleadoService = empleadoService;
        this.puestoService = puestoService;
        this.sucursalService = sucursalService;
        this.reporteService = reporteService;
    }

    // ----------- EMPRESAS ---------------
    @GetMapping("/empresas")
    public String listarEmpresas(Model model) {
        model.addAttribute("empresas", empresaService.findAll());
        return "empresa/lista";
    }

    @GetMapping("/empresas/nuevo")
    public String nuevaEmpresaForm(Model model) {
        model.addAttribute("empresa", new Empresa());
        return "empresa/form";
    }

    @PostMapping("/empresas/guardar")
    public String guardarEmpresa(@ModelAttribute Empresa empresa, RedirectAttributes redirect) {
        empresaService.save(empresa);
        redirect.addFlashAttribute("success", "Empresa guardada correctamente");
        return "redirect:/empresas";
    }

    @GetMapping("/empresas/editar/{id}")
    public String editarEmpresaForm(@PathVariable Integer id, Model model) {
        empresaService.findById(id).ifPresent(e -> model.addAttribute("empresa", e));
        return "empresa/form";
    }

    @GetMapping("/empresas/eliminar/{id}")
    public String eliminarEmpresa(@PathVariable Integer id, RedirectAttributes redirect) {
        empresaService.delete(id);
        redirect.addFlashAttribute("success", "Empresa eliminada correctamente");
        return "redirect:/empresas";
    }

    //------------- DEPARTAMENTOS ----------------
    @GetMapping("/departamentos")
    public String listarDepartamentos(Model model) {
        model.addAttribute("departamentos", departamentoService.findAll());
        model.addAttribute("empresas", empresaService.findAll());
        return "departamento/lista";
    }

    @GetMapping("/departamentos/nuevo")
    public String nuevoDepartamentoForm(Model model) {
        model.addAttribute("departamento", new Departamento());
        model.addAttribute("empresas", empresaService.findAll());
        return "departamento/form";
    }

    @PostMapping("/departamentos/guardar")
    public String guardarDepartamento(@ModelAttribute Departamento departamento, RedirectAttributes redirect) {
        departamentoService.save(departamento);
        redirect.addFlashAttribute("success", "Departamento guardado correctamente");
        return "redirect:/departamentos";
    }

    @GetMapping("/departamentos/editar/{id}")
    public String editarDepartamentoForm(@PathVariable Integer id, Model model) {
        departamentoService.findById(id).ifPresent(d -> model.addAttribute("departamento", d));
        model.addAttribute("empresas", empresaService.findAll());
        return "departamento/form";
    }

    @GetMapping("/departamentos/eliminar/{id}")
    public String eliminarDepartamento(@PathVariable Integer id, RedirectAttributes redirect) {
        departamentoService.delete(id);
        redirect.addFlashAttribute("success", "Departamento eliminado correctamente");
        return "redirect:/departamentos";
    }

    // --------------- EMPLEADOS ----------------
    @GetMapping("/empleados")
    public String listarEmpleados(Model model) {
        model.addAttribute("empleados", empleadoService.findAll());
        model.addAttribute("departamentos", departamentoService.findAll());
        model.addAttribute("puestos", puestoService.findAll()); // 🔹 agregado para mostrar el select
        return "empleado/lista";
    }

    @GetMapping("/empleados/nuevo")
    public String nuevoEmpleadoForm(Model model) {
        model.addAttribute("empleado", new Empleado());
        model.addAttribute("departamentos", departamentoService.findAll());
        model.addAttribute("puestos", puestoService.findAll());
        return "empleado/form";
    }

    @PostMapping("/empleados/guardar")
    public String guardarEmpleado(@ModelAttribute Empleado empleado, RedirectAttributes redirect) {
        empleadoService.save(empleado);
        redirect.addFlashAttribute("success", "Empleado guardado correctamente");
        return "redirect:/empleados";
    }

    @GetMapping("/empleados/editar/{id}")
    public String editarEmpleadoForm(@PathVariable Integer id, Model model) {
        empleadoService.findById(id).ifPresent(e -> model.addAttribute("empleado", e));
        model.addAttribute("departamentos", departamentoService.findAll());
        model.addAttribute("puestos", puestoService.findAll());
        return "empleado/form";
    }

    @GetMapping("/empleados/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable Integer id, RedirectAttributes redirect) {
        empleadoService.delete(id);
        redirect.addFlashAttribute("success", "Empleado eliminado correctamente");
        return "redirect:/empleados";
    }

    // --------------- PUESTOS ----------------
    @GetMapping("/puestos")
    public String listarPuestos(Model model) {
        model.addAttribute("puestos", puestoService.findAll());
        return "puesto/lista";
    }

    @GetMapping("/puestos/nuevo")
    public String nuevoPuestoForm(Model model) {
        model.addAttribute("puesto", new Puestos());
        return "puesto/form";
    }

    @PostMapping("/puestos/guardar")
    public String guardarPuesto(@ModelAttribute Puestos puesto, RedirectAttributes redirect) {
        puestoService.save(puesto);
        redirect.addFlashAttribute("success", "Puesto guardado correctamente");
        return "redirect:/puestos";
    }

    @GetMapping("/puestos/editar/{id}")
    public String editarPuestoForm(@PathVariable Integer id, Model model) {
        puestoService.findById(id).ifPresent(p -> model.addAttribute("puesto", p));
        return "puesto/form";
    }

    @GetMapping("/puestos/eliminar/{id}")
    public String eliminarPuesto(@PathVariable Integer id, RedirectAttributes redirect) {
        puestoService.delete(id);
        redirect.addFlashAttribute("success", "Puesto eliminado correctamente");
        return "redirect:/puestos";
    }

    // --------------- SUCURSALES ----------------
    @GetMapping("/sucursales")
    public String listarSucursales(Model model) {
        model.addAttribute("sucursales", sucursalService.findAll());
        model.addAttribute("empresas", empresaService.findAll());
        return "sucursal/lista";
    }

    @GetMapping("/sucursales/nuevo")
    public String nuevaSucursalForm(Model model) {
        model.addAttribute("sucursal", new Sucursales());
        model.addAttribute("empresas", empresaService.findAll());
        return "sucursal/form";
    }

    @PostMapping("/sucursales/guardar")
    public String guardarSucursal(@ModelAttribute Sucursales sucursal, RedirectAttributes redirect) {
        sucursalService.save(sucursal);
        redirect.addFlashAttribute("success", "Sucursal guardada correctamente");
        return "redirect:/sucursales";
    }

    @GetMapping("/sucursales/editar/{id}")
    public String editarSucursalForm(@PathVariable Integer id, Model model) {
        sucursalService.findById(id).ifPresent(s -> model.addAttribute("sucursal", s));
        model.addAttribute("empresas", empresaService.findAll());
        return "sucursal/form";
    }

    @GetMapping("/sucursales/eliminar/{id}")
    public String eliminarSucursal(@PathVariable Integer id, RedirectAttributes redirect) {
        sucursalService.delete(id);
        redirect.addFlashAttribute("success", "Sucursal eliminada correctamente");
        return "redirect:/sucursales";
    }

    // ----------- VISTAS REPORTES --------------

    @GetMapping("/reporte")
    public String listaReporte (Model model){
        model.addAttribute("reporte", reporteService.findAll());
        return "reporte/lista";
    }
}

