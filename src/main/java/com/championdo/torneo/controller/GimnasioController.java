package com.championdo.torneo.controller;

import com.championdo.torneo.configuration.SessionData;
import com.championdo.torneo.entity.User;
import com.championdo.torneo.mapper.MapperUser;
import com.championdo.torneo.model.*;
import com.championdo.torneo.service.*;
import com.championdo.torneo.service.impl.CargasInicialesClienteService;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/gimnasio")
public class GimnasioController {

    @Autowired
    private FormularioService formularioService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private InscripcionTaekwondoService inscripcionTaekwondoService;
    @Autowired
    private Menu2Service menu2Service;
    @Autowired
    private PdfService pdfService;
    @Autowired
    private SeguridadService seguridadService;
    @Autowired
    private PrincipalService principalService;
    @Autowired
    private GimnasioMenu2Service gimnasioMenu2Service;
    @Autowired
    private SessionData sessionData;
    @Autowired
    private MapperUser mapperUser;



    @Autowired
    private CargasInicialesClienteService cargasInicialesClienteService;
    @Autowired
    private GimnasioService gimnasioService;
    @Autowired
    private TorneoGimnasioService torneoGimnasioService;
    @Autowired
    private UserService userService;
    @Autowired
    private UtilService utilService;

    @GetMapping("/tipoInscripcion")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView tipoInscripcion(ModelAndView modelAndView) {
        modelAndView.setViewName("formularioInscripcionSeleccionGimnasio");
        User usuario = principalService.cargaBasicaCompleta(modelAndView);
        List<InscripcionTaekwondoModel> inscripcionTaekwondoModelList = inscripcionTaekwondoService.findByMayorDni(usuario.getUsername());
        if (!inscripcionTaekwondoModelList.isEmpty()) {
            modelAndView.addObject("inscripciones", inscripcionTaekwondoModelList);
        }
        modelAndView.addObject("operativaOriginal", Constantes.INSCRIPCION_TAEKWONDO);
        modelAndView.addObject("inscripcionA", "Taekwondo");
        modelAndView.addObject("controller", "gimnasio");
        modelAndView.addObject("gimnasios", gimnasioService.findByMenu2Url("/gimnasio/tipoInscripcion"));
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/tipoInscripcionConGimnasio/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView tipoInscripcionConGimnasio(ModelAndView modelAndView, @PathVariable Integer id) {
        modelAndView.setViewName("gimnasio/formularioTipoInscripcionGimnasio");
        User usuario = principalService.cargaBasicaCompleta(modelAndView);
        sessionData.setGimnasioModel(gimnasioService.findById(id));
        List<InscripcionTaekwondoModel> inscripcionTaekwondoModelList = inscripcionTaekwondoService.findByMayorDni(usuario.getUsername());
        if (!inscripcionTaekwondoModelList.isEmpty()) {
            for (InscripcionTaekwondoModel inscripcionTaekwondoModel : inscripcionTaekwondoModelList) {
                if (!inscripcionTaekwondoModel.isAutorizadoMenor()) {
                    modelAndView.addObject("ocultarAdulto", "ocultarAdulto");
                }
            }
            modelAndView.addObject("inscripciones", inscripcionTaekwondoModelList);
        }
        modelAndView.addObject("operativaOriginal", Constantes.INSCRIPCION_TAEKWONDO);
        modelAndView.addObject("inscripcionA", "Taekwondo");
        modelAndView.addObject("controller", "gimnasio");
        modelAndView.addObject("gimnasio", gimnasioService.findById(id));
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/formularioInscripcion/{id}/{tipo}/{licencia}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView formularioInscripcion(ModelAndView modelAndView, @PathVariable Integer id, @PathVariable String tipo, @PathVariable String licencia) {
        User user = principalService.cargaBasicaCompleta(modelAndView);
        UserModel userModel = mapperUser.entity2Model(user);
        modelAndView.addObject("accountBoxEnable", Boolean.parseBoolean(inscripcionTaekwondoService.getAccountBoxEnable(sessionData.getGimnasioModel().getId()).getValor()));
        if ("infantil".equalsIgnoreCase(tipo)) {
            modelAndView.setViewName("gimnasio/formularioInscMenorGimnasio");
            modelAndView.addObject("userAutorizacionModel", formularioService.formularioInscMenorOInclusivo(userModel, true));
        } else {
            modelAndView.setViewName("gimnasio/formularioInscPropiaGimnasio");
            modelAndView.addObject("userAutorizacionModel", formularioService.formularioInscPropiaGimnasio(userModel));
        }
        modelAndView.addObject("licencia", "con licencia".equals(licencia));
        formularioService.cargarDesplegables(modelAndView, sessionData.getGimnasioModel().getId());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/gaurdarPropia")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView gaurdarPropia(@ModelAttribute("userAutorizacionModel") UserAutorizacionModel userAutorizacionModel) {
        return logicaComunGuardar(userAutorizacionModel, Boolean.FALSE);
    }

    @PostMapping("/guardarMenor")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView guardarMenor(@ModelAttribute("userAutorizacionModel") UserAutorizacionModel userAutorizacionModel) {
        return logicaComunGuardar(userAutorizacionModel, Boolean.TRUE);
    }

    @GetMapping("/getInscripcion/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getInscripcion(ModelAndView modelAndView, @PathVariable int id) {
        principalService.cargaBasicaCompleta(modelAndView);
        InscripcionTaekwondoModel inscripcionTaekwondoModel = inscripcionTaekwondoService.findById(id);
        modelAndView.addObject("deleteEnable", Boolean.parseBoolean(inscripcionTaekwondoService.getDeleteEnable(inscripcionTaekwondoModel.getCodigoGimnasio()).getValor()));
        if (!inscripcionTaekwondoModel.isAutorizadoMenor()) {
            modelAndView.setViewName("gimnasio/vistaInscPropiaGimnasio");
        } else {
            modelAndView.setViewName("gimnasio/vistaInscMenorGimnasio");
        }
        modelAndView.addObject("inscripcion", inscripcionTaekwondoModel);
        modelAndView.addObject("pdfModel", new PdfModel());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/descargarPdf")
    @PreAuthorize("isAuthenticated()")
    public void descargarPdf(@ModelAttribute("pdfModel") PdfModel pdfModel, HttpServletResponse response) {
        pdfService.descargarArchivo(pdfModel, response, pdfModel.getSeccion());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), "Descarga de documento correcta", getClass());
    }

    @GetMapping("/eliminarInscripcion/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ModelAndView eliminarInscripcion(ModelAndView modelAndView, @PathVariable int id) {
        User usuario = principalService.cargaBasicaCompleta(modelAndView);
        InscripcionTaekwondoModel inscripcionTaekwondoModel = inscripcionTaekwondoService.findById(id);
        inscripcionTaekwondoService.delete(inscripcionTaekwondoModel);
        emailService.confirmAdminDelete(inscripcionTaekwondoModel.getCodigoGimnasio(), "gimnasio",
                usuario, inscripcionTaekwondoModel.getAutorizadoNombre());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return tipoInscripcion(modelAndView);
    }

    @GetMapping("/normativa-sepa/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView normativaSepa(ModelAndView modelAndView, @PathVariable int id) {
        LoggerMapper.methodIn(Level.INFO, "gimnasio/normativa-sepa", id, getClass());
        modelAndView.setViewName("gimnasio/normativaSepaGimnasio");
        User usuario = principalService.cargaBasicaCompleta(modelAndView);
        InscripcionTaekwondoModel inscripcion = inscripcionTaekwondoService.findById(id);
        modelAndView.addObject("inscripcion", inscripcion);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/normativa-sepa-firmado")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView normativaSepaFirmado(ModelAndView modelAndView, @RequestParam("idInscripcion") Integer idInscripcion, @RequestParam("file") MultipartFile file) {

        LoggerMapper.methodIn(Level.INFO, "gimnasio/normativa-sepa", idInscripcion, getClass());
        InscripcionTaekwondoModel inscripcionTaekwondoModel = inscripcionTaekwondoService.findById(idInscripcion);
        if(pdfService.subirArchivo(pdfService.getPdfInscripcionTaekwondo(inscripcionTaekwondoModel), file, Constantes.SECCION_NORMATIVA_SEPA_FIRMADO)) {
            inscripcionTaekwondoModel.setDomiciliacionSEPAFirmada(Boolean.TRUE);
            inscripcionTaekwondoModel.setExtensionSEPAFirmado(pdfService.getFileExtension(file));
            inscripcionTaekwondoService.update(inscripcionTaekwondoModel);
            try {
                emailService.confirmAdminSepaSigned(inscripcionTaekwondoModel);
            } catch (Exception e) {
                LoggerMapper.log(Level.ERROR, "gimnasio/normativa-sepa", e.getMessage(), getClass());
            }
            LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
            return tipoInscripcion(modelAndView);
        } else {
            modelAndView.addObject("subidaError", "Error en la subida del archivo");
            LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
            return normativaSepa(modelAndView, idInscripcion);
        }
    }

    @GetMapping("/detalle/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView detalle(ModelAndView modelAndView, @PathVariable int id) {
        LoggerMapper.methodIn(Level.INFO, "gimnasio/detalle", id, getClass());
        modelAndView.setViewName("gimnasio/detalle");
        User usuario = principalService.cargaBasicaCompleta(modelAndView);
        GimnasioModel gimnasioModel = gimnasioService.findById(id);
        modelAndView.addObject("gimnasio", gimnasioModel);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    private ModelAndView logicaComunGuardar (UserAutorizacionModel userAutorizacionModel, boolean menor) {

        String recurso = (menor ? "guardarMenor" : "gaurdarPropia");
        LoggerMapper.methodIn(Level.INFO, "gimnasio/" + recurso, userAutorizacionModel, getClass());

        ModelAndView modelAndView = new ModelAndView();
        User userLogged = principalService.cargaBasicaCompleta(modelAndView);
        if (menor) {
            formularioService.fillObjects(userAutorizacionModel.getAutorizado());
        }
        formularioService.fillObjects(userAutorizacionModel.getMayorAutorizador());
        InscripcionTaekwondoModel inscripcionTaekwondoModel = inscripcionTaekwondoService.add(userAutorizacionModel, sessionData.getGimnasioModel().getId());
        FirmaCodigoModel firmaCodigoModel = new FirmaCodigoModel(inscripcionTaekwondoModel.getId(),
                seguridadService.obtenerCodigo(), inscripcionTaekwondoModel.getMayorDni(),
                "formularioInscFinalizada", Constantes.INSCRIPCION_TAEKWONDO, sessionData.getGimnasioModel().getId());
        modelAndView = seguridadService.enviarCodigoFirma(modelAndView, firmaCodigoModel, userLogged);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;

    }





    @GetMapping("/customers")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView customers(ModelAndView modelAndView) {
        modelAndView.setViewName("management/customers");
        principalService.cargaBasicaCompleta(modelAndView);
        modelAndView.addObject("customerList", gimnasioService.findAllOrderByNombreGimnasioAsc());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @GetMapping("/customers/{id}")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView customersId(ModelAndView modelAndView,@PathVariable int id) {
        LoggerMapper.methodIn(Level.INFO, "customersId", "id: " + id, this.getClass());
        modelAndView.setViewName("management/updateCustomer");
        principalService.cargaBasicaCompleta(modelAndView);
        modelAndView.addObject("customer", gimnasioService.findById(id));
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/updateCustomer")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView updateCustomer(ModelAndView modelAndView, @ModelAttribute("customer") GimnasioModel customer) {
        LoggerMapper.methodIn(Level.INFO, "updateCustomer", customer, this.getClass());
        User user = principalService.cargaBasicaCompleta(modelAndView);
        customer.setUsuarioModificacion(user.getUsername());
        try {
            gimnasioService.update(customer);
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
        modelAndView.addObject("customer", new GimnasioModel());
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return modelAndView;
    }

    @PostMapping("/customers")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public ModelAndView addCustomer(ModelAndView modelAndView, @ModelAttribute("customer") GimnasioModel customer) {
        LoggerMapper.methodIn(Level.INFO, "addCustomer", customer, this.getClass());
        User user = principalService.cargaBasicaCompleta(modelAndView);
        customer.setUsuarioModificacion(user.getUsername());
        int idCustomer = 0;
        try {
            customer = gimnasioService.add(customer);
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
            utilService.addFromRoot(customer);
            cargasInicialesClienteService.cargasCintPoomCat(idCustomer);
            LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
            return customers(modelAndView);
        } catch (Exception e) {
            if(idCustomer != 0) {
                cargasInicialesClienteService.eliminacionesCatPoomCint(idCustomer);
                utilService.deleteFromRoot(idCustomer);
                gimnasioService.delete(idCustomer);
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
        torneoGimnasioService.deleteByCodigoGimnasio(id);
        gimnasioService.enableDisable(id, Boolean.FALSE);
        //Deshabilito el cliente pero no lo borro ya que Torneo (que tampoco se borran los torneos) tira del id y para que no se eliminen las inscripciones a los usuarios
        //TODO DAMIAN esto está mal pensado. Primero creo que se borran los torneos arriba. Segundo, si llego a volver a habilitar el gimnasio se eliminaron sus CatPoomCint
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
        return customers(modelAndView);
    }

}
