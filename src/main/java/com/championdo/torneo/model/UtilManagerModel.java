package com.championdo.torneo.model;

import com.championdo.torneo.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UtilManagerModel {

    private int id;
    private String hostPageName;
    private String email;
    private String password;
    private String emailHost;
    private String emailPort;

    @Override
    public String toString() {
        return "UtilManager{" +
                "id=" + id +
                ", hostPageName='" + hostPageName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + Utils.ofuscar(password) + '\'' +
                ", emailHost='" + emailHost + '\'' +
                ", emailPort='" + emailPort + '\'' +
                '}';
    }
}
