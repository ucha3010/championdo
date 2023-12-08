package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Mandato;
import com.championdo.torneo.exception.SenderException;
import com.championdo.torneo.exception.ValidationException;
import com.championdo.torneo.mapper.MapperMandato;
import com.championdo.torneo.model.FirmaCodigoModel;
import com.championdo.torneo.model.InscripcionTaekwondoModel;
import com.championdo.torneo.model.MandatoModel;
import com.championdo.torneo.model.PdfModel;
import com.championdo.torneo.repository.MandatoRepository;
import com.championdo.torneo.service.EmailService;
import com.championdo.torneo.service.MandatoService;
import com.championdo.torneo.service.PdfService;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.Utils;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    public List<MandatoModel> findByDniMandante(int codigoGimnasio, String dniMandante) {
        return fillModelList(mandatoRepository.findByCodigoGimnasioAndDniMandanteOrderByFechaAltaDesc(codigoGimnasio, dniMandante));
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
        return add(mandatoModel);
    }

    @Override
    public void delete(int idMandato) {
        mandatoRepository.deleteById(idMandato);
    }

    @Override
    public void fillMandato(MandatoModel mandatoModel, boolean adulto, int codigoGimnasio) {
        Calendar calendar = GregorianCalendar.getInstance();
        mandatoModel.setFechaAlta(calendar.getTime());
        mandatoModel.setTemporada(Utils.calculateSeason(calendar.getTime()));
        mandatoModel.setAdulto(adulto);
        mandatoModel.setCodigoGimnasio(codigoGimnasio);
    }

    @Override
    public MandatoModel fromInscripcionTaekwondoToMandato(InscripcionTaekwondoModel inscripcionTaekwondoModel) {
        MandatoModel mandatoModel = new MandatoModel();
        fillMandato(mandatoModel, inscripcionTaekwondoModel.isMayorLicencia(), inscripcionTaekwondoModel.getCodigoGimnasio());
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
        File pdfMandato = pdfService.generarPdfMandato(pdfModel);
        files.add(pdfMandato);
        mandatoModel.setMandatoFirmado(Boolean.TRUE);
        update(mandatoModel);
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
            if (mandatoModel.isAdulto() && mandato.isAdulto()) {
                throw new ValidationException(Constantes.AVISO_MANDATO_ADULTO_YA_EXISTE, "Ya existe un mandato de " + mandatoModel.getNombreMandante()
                        + " " + mandatoModel.getApellido1Mandante() + (mandatoModel.getApellido2Mandante() != null ? " " + mandatoModel.getApellido2Mandante() : "")
                        + " para la temporada " + mandatoModel.getTemporada());
            } else if (mandatoModel.isAdulto() && !mandato.isAdulto() && StringUtils.isNullOrEmpty(mandato.getDniAutorizado())) {
                throw new ValidationException(Constantes.AVISO_MANDATO_DNI_ADULTO_YA_USADO_PARA_UN_MENOR, "Con el DNI " + mandatoModel.getDniMandante()
                        + " ya se hizo un mandato para la temporada " + mandatoModel.getTemporada() + " para un menor o inclusivo."
                        + " Por favor contacte con el gimnasio para realizar la modificación necesaria.");
            } else if (!mandatoModel.isAdulto() && StringUtils.isNullOrEmpty(mandatoModel.getDniAutorizado())
                    && !mandato.isAdulto() && StringUtils.isNullOrEmpty(mandato.getDniAutorizado())) {
                throw new ValidationException(Constantes.AVISO_MANDATO_DNI_ADULTO_YA_USADO_PARA_OTRO_MENOR, "Con el DNI " + mandatoModel.getDniMandante()
                        + " ya se hizo un mandato para la temporada " + mandatoModel.getTemporada() + " para otro menor o inclusivo."
                        + " Por favor rellene el DNI del autorizado o contacte con el gimnasio para realizar la modificación necesaria.");
            } else if (!mandatoModel.isAdulto() && StringUtils.isNullOrEmpty(mandatoModel.getDniAutorizado()) && mandato.isAdulto()) {
                throw new ValidationException(Constantes.AVISO_MANDATO_DNI_ADULTO_YA_USADO_EN_INSCRIPCION_ADULTO, "Con el DNI " + mandatoModel.getDniMandante()
                        + " ya se hizo un mandato para la temporada " + mandatoModel.getTemporada() + " para "
                        + mandatoModel.getNombreMandante() + " " + mandatoModel.getApellido1Mandante()
                        + (mandatoModel.getApellido2Mandante() != null ? " " + mandatoModel.getApellido2Mandante() : "")
                        + ". Por favor contacte con el gimnasio para realizar la modificación necesaria.");
            } else if (!StringUtils.isNullOrEmpty(mandatoModel.getDniAutorizado()) && !StringUtils.isNullOrEmpty(mandato.getDniAutorizado())
                    && mandatoModel.getDniAutorizado().equals(mandato.getDniAutorizado())) {
                throw new ValidationException(Constantes.AVISO_MANDATO_MENOR_YA_EXISTE, "Ya existe un mandato de menor o inclusivo con el DNI "
                        + " " + mandatoModel.getDniAutorizado() + " para la temporada " + mandatoModel.getTemporada());
            }
        }
    }
}
