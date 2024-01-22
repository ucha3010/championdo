package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.GimnasioRootModel;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.service.*;
import com.championdo.torneo.service.impl.CargasInicialesClienteService;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/gimnasioRoot")
public class GimnasioRootController {

    @Autowired
    private GimnasioRootService gimnasioRootService;
    @Autowired
    private CargasInicialesClienteService cargasInicialesClienteService;
    @Autowired
    private GimnasioService gimnasioService;
    @Autowired
    private PrincipalService principalService;
    @Autowired
    private TorneoGimnasioService torneoGimnasioService;
    @Autowired
    private UserService userService;
    @Autowired
    private UtilService utilService;

    @GetMapping("/customers")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView customers(ModelAndView modelAndView) {
        modelAndView.setViewName("management/customers");
        principalService.cargaBasicaCompleta(modelAndView);
        modelAndView.addObject("customerList", gimnasioRootService.findAllOrderByNombreGimnasioAsc());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/customers/{id}")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView customersId(ModelAndView modelAndView,@PathVariable int id) {
        LoggerMapper.methodIn(Level.INFO, "customersId", "id: " + id, this.getClass());
        modelAndView.setViewName("management/updateCustomer");
        principalService.cargaBasicaCompleta(modelAndView);
        modelAndView.addObject("customer", gimnasioRootService.findById(id));
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/updateCustomer")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView updateCustomer(ModelAndView modelAndView, @ModelAttribute("customer") GimnasioRootModel customer) {
        LoggerMapper.methodIn(Level.INFO, "updateCustomer", customer, this.getClass());
        User user = principalService.cargaBasicaCompleta(modelAndView);
        customer.setUsuarioModificacion(user.getUsername());
        try {
            gimnasioRootService.update(customer);
            modelAndView.addObject("updateOk", "Actualización correcta");
        } catch (Exception e) {
            modelAndView.addObject("updateProblem", "Hubo un problema con la actualización");
            LoggerMapper.log(Level.ERROR, "updateCustomer", e.getMessage(), this.getClass());
        }
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return customersId(modelAndView, customer.getId());
    }

    @GetMapping("/formNewCustomer")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView formNewCustomer(ModelAndView modelAndView) {
        modelAndView.setViewName("management/addCustomer");
        principalService.cargaBasicaCompleta(modelAndView);
        modelAndView.addObject("customer", new GimnasioRootModel());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/customers")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView addCustomer(ModelAndView modelAndView, @ModelAttribute("customer") GimnasioRootModel customer) {
        LoggerMapper.methodIn(Level.INFO, "addCustomer", customer, this.getClass());
        User user = principalService.cargaBasicaCompleta(modelAndView);
        customer.setUsuarioModificacion(user.getUsername());
        int idCustomer = 0;
        try {
            customer = gimnasioRootService.add(customer);
            idCustomer = customer.getId();
            boolean userExist = false;
            for (UserModel userModel: userService.findAll()) {
                if (userModel.getUsername().equalsIgnoreCase(customer.getCifNif())){
                    userExist = true;
                    break;
                }
            }
            if (!userExist) {
                userService.addFromRoot(customer);
            }
            //gimnasioService.addFromRoot(customer);
            utilService.addFromRoot(customer);
            cargasInicialesClienteService.cargasCintPoomCat(idCustomer);
            LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
            return customers(modelAndView);
        } catch (Exception e) {
            if(idCustomer != 0) {
                cargasInicialesClienteService.eliminacionesCatPoomCint(idCustomer);
                utilService.deleteFromRoot(idCustomer);
//                userService.deleteFromRoot(idCustomer);
                //gimnasioService.deleteFromRoot(idCustomer);
                gimnasioRootService.delete(idCustomer);
            }
            modelAndView.setViewName("management/addCustomer");
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("addProblem", "Hubo un problema con la inserción - " + e.getMessage());
            LoggerMapper.log(Level.ERROR, "addCustomer", e.getMessage(), this.getClass());
        }
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/resetCintPoomCat/{id}")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView resetCintPoomCat(ModelAndView modelAndView, @PathVariable int id) {
        LoggerMapper.methodIn(Level.INFO, "resetCintPoomCat", "id: " + id, this.getClass());
        principalService.cargaBasicaCompleta(modelAndView);
        cargasInicialesClienteService.eliminacionesCatPoomCint(id);
        cargasInicialesClienteService.cargasCintPoomCat(id);
        modelAndView.addObject("resetOk", "Reseteo realizado con éxito");
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return customersId(modelAndView, id);
    }

    @GetMapping("/deleteCustomer/{id}")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView deleteCustomer(ModelAndView modelAndView,@PathVariable int id) {
        LoggerMapper.methodIn(Level.INFO, "deleteCustomer", "id: " + id, this.getClass());
        principalService.cargaBasicaCompleta(modelAndView);
        cargasInicialesClienteService.eliminacionesCatPoomCint(id);
        utilService.deleteFromRoot(id);
/*        if (userService.findAll(id).size() == 1) {
            userService.deleteFromRoot(id);
        }*/
        //gimnasioService.deleteFromRoot(id);
        torneoGimnasioService.deleteByCodigoGimnasio(id);
        gimnasioRootService.enableDisable(id, Boolean.FALSE);
        //Deshabilito el cliente pero no lo borro ya que Torneo (que tampoco se borran los torneos) tira del id y para que no se eliminen las inscripciones a los usuarios
        //TODO DAMIAN esto está mal pensado. Primero creo que se borran los torneos arriba. Segundo, si llego a volver a habilitar el gimnasio se eliminaron sus CatPoomCint
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return customers(modelAndView);
    }

}
