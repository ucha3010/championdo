package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Mandato;
import com.championdo.torneo.exception.SenderException;
import com.championdo.torneo.exception.ValidationException;
import com.championdo.torneo.mapper.MapperMandato;
import com.championdo.torneo.model.*;
import com.championdo.torneo.repository.MandatoRepository;
import com.championdo.torneo.service.EmailService;
import com.championdo.torneo.service.GimnasioService;
import com.championdo.torneo.service.MandatoService;
import com.championdo.torneo.service.PdfService;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.Utils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service()
public class MandatoServiceImpl implements MandatoService {

    @Autowired
    private MandatoRepository mandatoRepository;
    @Autowired
    private MapperMandato mapperMandato;
    @Autowired
    private EmailService emailService;
    @Autowired
    private GimnasioService gimnasioService;
    @Autowired
    private PdfService pdfService;

    @Override
    public List<MandatoModel> findAll(int codigoGimnasio) {
        return fillModelList(mandatoRepository.findByCodigoGimnasioOrderByFechaAltaDesc(codigoGimnasio));
    }

    @Override
    public MandatoModel findById(int id) {
        try {
            return mapperMandato.entity2Model(mandatoRepository.getById(id));
        } catch (EntityNotFoundException e) {
            return new MandatoModel();
        }
    }

    @Override
    public List<MandatoModel> findByDniMandante(String dniMandante) {
        return fillModelList(mandatoRepository.findByDniMandanteOrderByFechaAltaDesc(dniMandante));
    }

    @Override
    public List<MandatoModel> findByDniMandanteAndMandatoFirmadoFalse(int codigoGimnasio, String dniMandante) {
        return fillModelList(mandatoRepository.findByCodigoGimnasioAndDniMandanteAndMandatoFirmadoFalseOrderByFechaAltaDesc(codigoGimnasio, dniMandante));
    }

    @Override
    public MandatoModel add(MandatoModel mandatoModel) throws ValidationException {
        List<Mandato> mandatoList = mandatoRepository.findByDniMandanteAndTemporadaAndMandatoFirmadoTrue(mandatoModel.getDniMandante(), mandatoModel.getTemporada());
        if (!mandatoList.isEmpty()) {
            validarMandato(mandatoModel, mandatoList);
        }
        return mapperMandato.entity2Model(mandatoRepository.save(mapperMandato.model2Entity(mandatoModel)));
    }

    @Override
    public MandatoModel update(MandatoModel mandatoModel) throws ValidationException {
        return mapperMandato.entity2Model(mandatoRepository.save(mapperMandato.model2Entity(mandatoModel)));
    }

    @Override
    public void delete(int idMandato) {
        mandatoRepository.deleteById(idMandato);
    }

    @Override
    public void fillMandato(MandatoModel mandatoModel, boolean adulto) {
        Calendar calendar = GregorianCalendar.getInstance();
        mandatoModel.setFechaAlta(calendar.getTime());
        mandatoModel.setTemporada(Utils.calculateSeason(calendar.getTime()));
        mandatoModel.setAdulto(adulto);
        mandatoModel.setNombreGimnasio(gimnasioService.findById(mandatoModel.getCodigoGimnasio()).getNombreGimnasio());
    }

    @Override
    public MandatoModel fromInscripcionTaekwondoToMandato(InscripcionTaekwondoModel inscripcionTaekwondoModel) {
        MandatoModel mandatoModel = new MandatoModel();
        fillMandato(mandatoModel, inscripcionTaekwondoModel.isMayorLicencia());
        mandatoModel.setNombreMandante(inscripcionTaekwondoModel.getMayorNombre());
        mandatoModel.setApellido1Mandante(inscripcionTaekwondoModel.getMayorApellido1());
        mandatoModel.setApellido2Mandante(inscripcionTaekwondoModel.getMayorApellido2());
        mandatoModel.setDniMandante(inscripcionTaekwondoModel.getMayorDni());
        mandatoModel.setCorreoMandante(inscripcionTaekwondoModel.getMayorCorreo());
        mandatoModel.setCalidad(inscripcionTaekwondoModel.getMayorCalidad());
        mandatoModel.setNombreAutorizado(inscripcionTaekwondoModel.getAutorizadoNombre());
        mandatoModel.setApellido1Autorizado(inscripcionTaekwondoModel.getAutorizadoApellido1());
        mandatoModel.setApellido2Autorizado(inscripcionTaekwondoModel.getAutorizadoApellido2());
        mandatoModel.setDniAutorizado(inscripcionTaekwondoModel.getAutorizadoDni());
        mandatoModel.setDomicilioCalle(inscripcionTaekwondoModel.getMayorDomicilioCalle());
        mandatoModel.setDomicilioNumero(inscripcionTaekwondoModel.getMayorDomicilioNumero());
        mandatoModel.setDomicilioOtros(inscripcionTaekwondoModel.getMayorDomicilioOtros());
        mandatoModel.setDomicilioLocalidad(inscripcionTaekwondoModel.getMayorDomicilioLocalidad());
        mandatoModel.setDomicilioCp(inscripcionTaekwondoModel.getMayorDomicilioCp());
        mandatoModel.setPais(inscripcionTaekwondoModel.getMayorPais());
        mandatoModel.setMandatoFirmado(true);
        return mandatoModel;
    }

    @Override
    public void crearEnviarArchivosInscripcionTaekwondo(FirmaCodigoModel firmaCodigoModel) throws SenderException, ValidationException {
        List<File> files = new ArrayList<>();
        MandatoModel mandatoModel = findById(firmaCodigoModel.getIdOperacion());
        PdfModel pdfModel = pdfService.getPdfMandato(mandatoModel);
        DocumentManagerModel documentManagerModel = pdfService.createPdfFederativeLicenseMandate(pdfModel, true);
        files.add(new File(documentManagerModel.getFullPath()));
        mandatoModel.setMandatoFirmado(Boolean.TRUE);
        add(mandatoModel);
        emailService.sendNewMandato(mandatoModel, files);
        emailService.confirmAdminNewMandato(mandatoModel);
    }

    private List<MandatoModel> fillModelList(List<Mandato> mandatoList) {
        List<MandatoModel> mandatoModelList = new ArrayList<>();
        for (Mandato Mandato: mandatoList) {
            mandatoModelList.add(mapperMandato.entity2Model(Mandato));
        }
        return mandatoModelList;
    }

    private void validarMandato(MandatoModel mandatoModel, List<Mandato> mandatoList) throws ValidationException{
        for (Mandato mandato : mandatoList) {
            if (mandatoModel.isAdulto() && mandato.isAdulto() && mandatoModel.getCodigoGimnasio() == mandato.getCodigoGimnasio()) {
                throw new ValidationException(Constantes.AVISO_MANDATO_ADULTO_YA_EXISTE, "Ya existe un mandato de " + mandatoModel.getNombreMandante()
                        + " " + mandatoModel.getApellido1Mandante() + (mandatoModel.getApellido2Mandante() != null ? " " + mandatoModel.getApellido2Mandante() : "")
                        + " para la temporada " + mandatoModel.getTemporada() + " en el gimnasio " + mandatoModel.getNombreGimnasio());
            } else if (mandatoModel.isAdulto() && !mandato.isAdulto() && Utils.isNullOrEmpty(mandato.getDniAutorizado())
                    && mandatoModel.getCodigoGimnasio() == mandato.getCodigoGimnasio()) {
                throw new ValidationException(Constantes.AVISO_MANDATO_DNI_ADULTO_YA_USADO_PARA_UN_MENOR, "Con el DNI " + mandatoModel.getDniMandante()
                        + " ya se hizo un mandato para la temporada " + mandatoModel.getTemporada() + " en el gimnasio " + mandatoModel.getNombreGimnasio()
                        + " para un menor o inclusivo. Es necesario que cada mandato vaya asociado a un DNI diferente.");
            } else if (!mandatoModel.isAdulto() && Utils.isNullOrEmpty(mandatoModel.getDniAutorizado())
                    && !mandato.isAdulto() && Utils.isNullOrEmpty(mandato.getDniAutorizado())
                    && mandatoModel.getCodigoGimnasio() == mandato.getCodigoGimnasio()) {
                throw new ValidationException(Constantes.AVISO_MANDATO_DNI_ADULTO_YA_USADO_PARA_OTRO_MENOR, "Con el DNI " + mandatoModel.getDniMandante()
                        + " ya se hizo un mandato para la temporada " + mandatoModel.getTemporada() + " en el gimnasio " + mandatoModel.getNombreGimnasio()
                        + " para otro menor o inclusivo. Por favor rellene el DNI del autorizado o contacte con el gimnasio para realizar la modificación"
                        + " necesaria.");
            } else if (!mandatoModel.isAdulto() && Utils.isNullOrEmpty(mandatoModel.getDniAutorizado()) && mandato.isAdulto()
                    && mandatoModel.getCodigoGimnasio() == mandato.getCodigoGimnasio()) {
                throw new ValidationException(Constantes.AVISO_MANDATO_DNI_ADULTO_YA_USADO_EN_INSCRIPCION_ADULTO, "Con el DNI " + mandatoModel.getDniMandante()
                        + " ya se hizo un mandato para la temporada " + mandatoModel.getTemporada() + " en el gimnasio " + mandatoModel.getNombreGimnasio()
                        + " para " + mandatoModel.getNombreMandante() + " " + mandatoModel.getApellido1Mandante()
                        + (mandatoModel.getApellido2Mandante() != null ? " " + mandatoModel.getApellido2Mandante() : "")
                        + ". Es necesario que cada mandato vaya asociado a un DNI diferente.");
            } else if (!Utils.isNullOrEmpty(mandatoModel.getDniAutorizado()) && !Utils.isNullOrEmpty(mandato.getDniAutorizado())
                    && mandatoModel.getDniAutorizado().equals(mandato.getDniAutorizado()) && mandatoModel.getCodigoGimnasio() == mandato.getCodigoGimnasio()) {
                throw new ValidationException(Constantes.AVISO_MANDATO_MENOR_YA_EXISTE, "Ya existe un mandato de menor o inclusivo con el DNI "
                        + " " + mandatoModel.getDniAutorizado() + " para la temporada " + mandatoModel.getTemporada() + " en el gimnasio "
                        + mandatoModel.getNombreGimnasio() + ". Es necesario que cada mandato vaya asociado a un DNI diferente.");
            }
        }
    }
}
