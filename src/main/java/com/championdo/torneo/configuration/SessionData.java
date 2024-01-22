package com.championdo.torneo.configuration;

import com.championdo.torneo.model.GimnasioModel;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionData {

    private GimnasioModel gimnasioModel;

    public GimnasioModel getGimnasioModel() {
        return gimnasioModel;
    }

    public void setGimnasioModel(GimnasioModel gimnasioModel) {
        this.gimnasioModel = gimnasioModel;
    }
}
