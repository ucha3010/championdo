package com.championdo.torneo.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Menu1Model {

    private int id;
    private boolean enabled;
    private String nombre;
    private int position;
    private List<Menu2Model> menu2ModelList;

}
