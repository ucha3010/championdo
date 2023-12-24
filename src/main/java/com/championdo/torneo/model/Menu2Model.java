package com.championdo.torneo.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Menu2Model {

    private int id;
    private int idMenu1;
    private boolean enabled;
    private String nombre;
    private int position;
    private String url;
    private String aviso;
    private List<GimnasioRootModel> gimnasioRootModelList;
    private boolean checked;

}
