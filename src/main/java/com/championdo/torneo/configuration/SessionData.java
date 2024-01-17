package com.championdo.torneo.configuration;

import com.championdo.torneo.model.GimnasioRootModel;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionData {

    private GimnasioRootModel gimnasioRootModel;

    public GimnasioRootModel getGimnasioRootModel() {
        return gimnasioRootModel;
    }

    public void setGimnasioRootModel(GimnasioRootModel gimnasioRootModel) {
        this.gimnasioRootModel = gimnasioRootModel;
    }
}
