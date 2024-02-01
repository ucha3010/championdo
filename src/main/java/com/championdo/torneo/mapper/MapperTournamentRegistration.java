package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.TournamentRegistration;
import com.championdo.torneo.model.TournamentRegistrationModel;
import org.springframework.stereotype.Component;

@Component
public class MapperTournamentRegistration {

    public TournamentRegistrationModel entity2Model(TournamentRegistration externObject) {

        TournamentRegistrationModel localObject = new TournamentRegistrationModel();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setRegistrationAdult(externObject.isRegistrationAdult());
            localObject.setRegistrationYoung(externObject.isRegistrationYoung());
            localObject.setRegistrationInclusive(externObject.isRegistrationInclusive());
            localObject.setRegistrationDate(externObject.getRegistrationDate());
            localObject.setTournamentDate(externObject.getTournamentDate());
            localObject.setTournamentName(externObject.getTournamentName());
            localObject.setTournamentAddress(externObject.getTournamentAddress());
            localObject.setCategory(externObject.getCategory());
            localObject.setIdGym(externObject.getIdGym());
            localObject.setIdTournament(externObject.getIdTournament());

            localObject.setRegisteredName(externObject.getRegisteredName());
            localObject.setRegistered1Lastname(externObject.getRegistered1Lastname());
            localObject.setRegistered2Lastname(externObject.getRegistered2Lastname());
            localObject.setRegisteredIdCard(externObject.getRegisteredIdCard());
            localObject.setRegisteredBirthdate(externObject.getRegisteredBirthdate());
            localObject.setRegisteredSex(externObject.getRegisteredSex());
            localObject.setRegisteredAddressStreet(externObject.getRegisteredAddressStreet());
            localObject.setRegisteredAddressNumber(externObject.getRegisteredAddressNumber());
            localObject.setRegisteredAddressOther(externObject.getRegisteredAddressOther());
            localObject.setRegisteredAddressCity(externObject.getRegisteredAddressCity());
            localObject.setRegisteredAddressPostcode(externObject.getRegisteredAddressPostcode());
            localObject.setGym(externObject.getGym());
            localObject.setCountry(externObject.getCountry());
            localObject.setBelt(externObject.getBelt());
            localObject.setPoomsae(externObject.getPoomsae());

            localObject.setAuthorizerName(externObject.getAuthorizerName());
            localObject.setAuthorizer1Lastname(externObject.getAuthorizer1Lastname());
            localObject.setAuthorizer2Lastname(externObject.getAuthorizer2Lastname());
            localObject.setAuthorizerIdCard(externObject.getAuthorizerIdCard());
            localObject.setRelationship(externObject.getRelationship());
            localObject.setAuthorizerAddressStreet(externObject.getAuthorizerAddressStreet());
            localObject.setAuthorizerAddressNumber(externObject.getAuthorizerAddressNumber());
            localObject.setAuthorizerAddressOther(externObject.getAuthorizerAddressOther());
            localObject.setAuthorizerAddressCity(externObject.getAuthorizerAddressCity());
            localObject.setAuthorizerAddressPostcode(externObject.getAuthorizerAddressPostcode());

            localObject.setPaid(externObject.isPaid());
            localObject.setPaymentDate(externObject.getPaymentDate());
            localObject.setNotes(externObject.getNotes());
        }
        return localObject;
    }

    public TournamentRegistration model2Entity(TournamentRegistrationModel externObject) {

        TournamentRegistration localObject = new TournamentRegistration();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setRegistrationAdult(externObject.isRegistrationAdult());
            localObject.setRegistrationYoung(externObject.isRegistrationYoung());
            localObject.setRegistrationInclusive(externObject.isRegistrationInclusive());
            localObject.setRegistrationDate(externObject.getRegistrationDate());
            localObject.setTournamentDate(externObject.getTournamentDate());
            localObject.setTournamentName(externObject.getTournamentName());
            localObject.setTournamentAddress(externObject.getTournamentAddress());
            localObject.setCategory(externObject.getCategory());
            localObject.setIdGym(externObject.getIdGym());
            localObject.setIdTournament(externObject.getIdTournament());

            localObject.setRegisteredName(externObject.getRegisteredName());
            localObject.setRegistered1Lastname(externObject.getRegistered1Lastname());
            localObject.setRegistered2Lastname(externObject.getRegistered2Lastname());
            localObject.setRegisteredIdCard(externObject.getRegisteredIdCard());
            localObject.setRegisteredBirthdate(externObject.getRegisteredBirthdate());
            localObject.setRegisteredSex(externObject.getRegisteredSex());
            localObject.setRegisteredAddressStreet(externObject.getRegisteredAddressStreet());
            localObject.setRegisteredAddressNumber(externObject.getRegisteredAddressNumber());
            localObject.setRegisteredAddressOther(externObject.getRegisteredAddressOther());
            localObject.setRegisteredAddressCity(externObject.getRegisteredAddressCity());
            localObject.setRegisteredAddressPostcode(externObject.getRegisteredAddressPostcode());
            localObject.setGym(externObject.getGym());
            localObject.setCountry(externObject.getCountry());
            localObject.setBelt(externObject.getBelt());
            localObject.setPoomsae(externObject.getPoomsae());

            localObject.setAuthorizerName(externObject.getAuthorizerName());
            localObject.setAuthorizer1Lastname(externObject.getAuthorizer1Lastname());
            localObject.setAuthorizer2Lastname(externObject.getAuthorizer2Lastname());
            localObject.setAuthorizerIdCard(externObject.getAuthorizerIdCard());
            localObject.setRelationship(externObject.getRelationship());
            localObject.setAuthorizerAddressStreet(externObject.getAuthorizerAddressStreet());
            localObject.setAuthorizerAddressNumber(externObject.getAuthorizerAddressNumber());
            localObject.setAuthorizerAddressOther(externObject.getAuthorizerAddressOther());
            localObject.setAuthorizerAddressCity(externObject.getAuthorizerAddressCity());
            localObject.setAuthorizerAddressPostcode(externObject.getAuthorizerAddressPostcode());

            localObject.setPaid(externObject.isPaid());
            localObject.setPaymentDate(externObject.getPaymentDate());
            localObject.setNotes(externObject.getNotes());
        }
        return localObject;
    }
}
