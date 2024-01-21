package com.championdo.torneo.entity;

import com.championdo.torneo.util.Utils;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "util_manager")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UtilManager {

    @Id
    @SequenceGenerator(name = "utilManagerGenerator", sequenceName = "CHANGE_LOW_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utilManagerGenerator")
    private int id;
    @Column(name = "hostPageName", length = 200)
    private String hostPageName;
    @Column(name = "email", length = 200)
    private String email;
    @Column(name = "password", length = 60)
    private String password;
    @Column(name = "emailHost", length = 200)
    private String emailHost;
    @Column(name = "emailPort", length = 5)
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
