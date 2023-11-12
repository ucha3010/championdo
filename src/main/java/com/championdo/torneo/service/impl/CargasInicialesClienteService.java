package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Categoria;
import com.championdo.torneo.entity.Cinturon;
import com.championdo.torneo.entity.Poomsae;
import com.championdo.torneo.repository.CategoriaRepository;
import com.championdo.torneo.repository.CinturonRepository;
import com.championdo.torneo.repository.PoomsaeRepository;
import com.championdo.torneo.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("cargasInicialesClienteService ")
public class CargasInicialesClienteService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CinturonRepository cinturonRepository;

    @Autowired
    private PoomsaeRepository poomsaeRepository;

    public void cargasCintPoomCat(int idGimnasioRoot) {
        cargaCinturones(idGimnasioRoot);
        cargaPoomsaes(idGimnasioRoot);
        cargaCategorias(idGimnasioRoot);
    }

    public void eliminacionesCatPoomCint (int idGimnasioRoot) {
        eliminarCategorias(idGimnasioRoot);
        eliminarPoomsaes(idGimnasioRoot);
        eliminarCinturones(idGimnasioRoot);
    }

    public void eliminarCategorias (int idGimnasioRoot) {
        List<Categoria> categoriaList = categoriaRepository.findByCodigoGimnasio(idGimnasioRoot);
        categoriaRepository.deleteAll(categoriaList);
    }

    public void eliminarPoomsaes (int idGimnasioRoot) {
        List<Poomsae> poomsaeList = poomsaeRepository.findByCodigoGimnasio(idGimnasioRoot);
        poomsaeRepository.deleteAll(poomsaeList);
    }

    public void eliminarCinturones (int idGimnasioRoot){
        List<Cinturon> cinturonList = cinturonRepository.findByCodigoGimnasio(idGimnasioRoot);
        cinturonRepository.deleteAll(cinturonList);
    }

    private void cargaCinturones(int idGimnasioRoot) {
        List<Cinturon> cinturonList = new ArrayList<>();
        cinturonList.add(new Cinturon(Constantes.BLANCO, 0, idGimnasioRoot));
        cinturonList.add(new Cinturon("Blanco Amarillo", 1, idGimnasioRoot));
        cinturonList.add(new Cinturon("Amarillo", 2, idGimnasioRoot));
        cinturonList.add(new Cinturon("Amarillo Naranja", 3, idGimnasioRoot));
        cinturonList.add(new Cinturon("Naranja", 4, idGimnasioRoot));
        cinturonList.add(new Cinturon("Naranja Verde", 5, idGimnasioRoot));
        cinturonList.add(new Cinturon("Verde", 6, idGimnasioRoot));
        cinturonList.add(new Cinturon("Verde Azul", 7, idGimnasioRoot));
        cinturonList.add(new Cinturon("Azul", 8, idGimnasioRoot));
        cinturonList.add(new Cinturon("Azul Rojo", 9, idGimnasioRoot));
        cinturonList.add(new Cinturon("Azul Marrón", 10, idGimnasioRoot));
        cinturonList.add(new Cinturon("Marrón", 11, idGimnasioRoot));
        cinturonList.add(new Cinturon("Marrón Rojo", 12, idGimnasioRoot));
        cinturonList.add(new Cinturon("Rojo", 13, idGimnasioRoot));
        cinturonList.add(new Cinturon("Rojo Negro", 14, idGimnasioRoot));
        cinturonList.add(new Cinturon("Rojo Negro 1º PUM", 15, idGimnasioRoot));
        cinturonList.add(new Cinturon("Rojo Negro 2º PUM", 16, idGimnasioRoot));
        cinturonList.add(new Cinturon("Rojo Negro 3º PUM", 17, idGimnasioRoot));
        cinturonList.add(new Cinturon("Rojo Negro 4º PUM", 18, idGimnasioRoot));
        cinturonList.add(new Cinturon("Negro 1º DAN", 19, idGimnasioRoot));
        cinturonList.add(new Cinturon("Negro 2º DAN", 20, idGimnasioRoot));
        cinturonList.add(new Cinturon("Negro 3º DAN", 21, idGimnasioRoot));
        cinturonList.add(new Cinturon("Negro 4º DAN", 22, idGimnasioRoot));
        cinturonList.add(new Cinturon("Negro 5º DAN", 23, idGimnasioRoot));
        cinturonList.add(new Cinturon("Negro 6º DAN", 24, idGimnasioRoot));
        cinturonList.add(new Cinturon("Negro 7º DAN", 25, idGimnasioRoot));
        cinturonList.add(new Cinturon("Negro 8º DAN", 26, idGimnasioRoot));
        cinturonList.add(new Cinturon("Negro 9º DAN", 27, idGimnasioRoot));
        cinturonList.add(new Cinturon("Negro 10º DAN", 28, idGimnasioRoot));
        cinturonRepository.saveAll(cinturonList);
    }

    private void cargaPoomsaes(int idGimnasioRoot) {
        List<Poomsae> poomsaeList = new ArrayList<>();
        poomsaeList.add(new Poomsae(Constantes.KICHO,0,idGimnasioRoot));
        poomsaeList.add(new Poomsae(Constantes.POOMSAE1,1,idGimnasioRoot));
        poomsaeList.add(new Poomsae(Constantes.POOMSAE2,2,idGimnasioRoot));
        poomsaeList.add(new Poomsae(Constantes.POOMSAE3,3,idGimnasioRoot));
        poomsaeList.add(new Poomsae(Constantes.POOMSAE4,4,idGimnasioRoot));
        poomsaeList.add(new Poomsae(Constantes.POOMSAE5,5,idGimnasioRoot));
        poomsaeList.add(new Poomsae(Constantes.POOMSAE6,6,idGimnasioRoot));
        poomsaeList.add(new Poomsae(Constantes.POOMSAE7,7,idGimnasioRoot));
        poomsaeList.add(new Poomsae(Constantes.POOMSAE8,8,idGimnasioRoot));
        poomsaeList.add(new Poomsae(Constantes.KORYO,9,idGimnasioRoot));
        poomsaeList.add(new Poomsae(Constantes.KUMGANG,10,idGimnasioRoot));
        poomsaeList.add(new Poomsae(Constantes.TAEBEK,11,idGimnasioRoot));
        poomsaeList.add(new Poomsae(Constantes.PYONGWON,12,idGimnasioRoot));
        poomsaeList.add(new Poomsae(Constantes.SYPCCHIN,13,idGimnasioRoot));
        poomsaeList.add(new Poomsae(Constantes.CHITAE,14,idGimnasioRoot));
        poomsaeList.add(new Poomsae(Constantes.CHUNGKWON,15,idGimnasioRoot));
        poomsaeList.add(new Poomsae(Constantes.JANSU,16,idGimnasioRoot));
        poomsaeList.add(new Poomsae(Constantes.ILIO,17,idGimnasioRoot));
        poomsaeList.add(new Poomsae(Constantes.INCLUSIVO,18,idGimnasioRoot));
        poomsaeRepository.saveAll(poomsaeList);
    }


    private void cargaCategorias(int idGimnasioRoot) {

        List<Categoria> categoriaList = new ArrayList<>();
        int position = 0;
        int edadInicio = 0;
        int edadFin = 0;
        String nombreCategoria = Constantes.INCLUSIVO;
        int positionCinturonInicio = 0;
        int positionCinturonFin = 28;
        String poomsae = Constantes.INCLUSIVO;
        List<Boolean> categorias = Arrays.asList(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        edadInicio = 3;
        edadFin = 3;
        nombreCategoria = "A";
        //positionCinturonInicio = 0;
        positionCinturonFin = 18;
        poomsae = Constantes.KICHO;
        categorias = Arrays.asList(Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE);
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        edadInicio = 4;
        edadFin = 4;
        nombreCategoria = "B";
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        edadInicio = 5;
        edadFin = 5;
        nombreCategoria = "C";
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        edadInicio = 6;
        edadFin = 6;
        nombreCategoria = "D";
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        edadInicio = 7;
        edadFin = 7;
        nombreCategoria = "E";
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        edadInicio = 8;
        edadFin = 8;
        nombreCategoria = "F";
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        edadInicio = 7;
        edadFin = 7;
        nombreCategoria = "A1";
        //positionCinturonInicio = 0;
        positionCinturonFin = 2;
        poomsae = Constantes.POOMSAE1;
        categorias = Arrays.asList(Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE);
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "A2";
        positionCinturonInicio = 3;
        positionCinturonFin = 5;
        poomsae = Constantes.POOMSAE1;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "A3";
        positionCinturonInicio = 6;
        positionCinturonFin = 18;
        poomsae = Constantes.POOMSAE1;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        edadInicio = 8;
        edadFin = 9;
        nombreCategoria = "B1";
        positionCinturonInicio = 0;
        positionCinturonFin = 3;
        poomsae = Constantes.POOMSAE1;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "B2";
        positionCinturonInicio = 4;
        positionCinturonFin = 7;
        poomsae = Constantes.POOMSAE2;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "B3";
        positionCinturonInicio = 8;
        positionCinturonFin = 18;
        poomsae = Constantes.POOMSAE3;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        edadInicio = 10;
        edadFin = 11;
        nombreCategoria = "C1";
        positionCinturonInicio = 0;
        positionCinturonFin = 3;
        poomsae = Constantes.POOMSAE1;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "C2";
        positionCinturonInicio = 4;
        positionCinturonFin = 7;
        poomsae = Constantes.POOMSAE2;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "C3";
        positionCinturonInicio = 8;
        positionCinturonFin = 13;
        poomsae = Constantes.POOMSAE3;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "C4";
        positionCinturonInicio = 14;
        positionCinturonFin = 18;
        poomsae = Constantes.KORYO;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        edadInicio = 12;
        edadFin = 13;
        nombreCategoria = "D1";
        positionCinturonInicio = 0;
        positionCinturonFin = 3;
        poomsae = Constantes.POOMSAE1;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "D2";
        positionCinturonInicio = 4;
        positionCinturonFin = 7;
        poomsae = Constantes.POOMSAE2;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "D3";
        positionCinturonInicio = 8;
        positionCinturonFin = 13;
        poomsae = Constantes.POOMSAE5;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "D4";
        positionCinturonInicio = 14;
        positionCinturonFin = 18;
        poomsae = Constantes.KORYO;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        edadInicio = 14;
        edadFin = 15;
        nombreCategoria = "E1";
        positionCinturonInicio = 0;
        positionCinturonFin = 3;
        poomsae = Constantes.POOMSAE1;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "E2";
        positionCinturonInicio = 4;
        positionCinturonFin = 7;
        poomsae = Constantes.POOMSAE2;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "E3";
        positionCinturonInicio = 8;
        positionCinturonFin = 13;
        poomsae = Constantes.POOMSAE5;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "E4";
        positionCinturonInicio = 14;
        positionCinturonFin = 18;
        poomsae = Constantes.KORYO;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        edadInicio = 16;
        edadFin = 18;
        nombreCategoria = "A1";
        positionCinturonInicio = 0;
        positionCinturonFin = 2;
        poomsae = Constantes.POOMSAE1;
        categorias = Arrays.asList(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE);
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "A2";
        positionCinturonInicio = 4;
        positionCinturonFin = 6;
        poomsae = Constantes.POOMSAE2;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "A3";
        positionCinturonInicio = 8;
        positionCinturonFin = 13;
        poomsae = Constantes.POOMSAE4;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "A4";
        positionCinturonInicio = 19;
        positionCinturonFin = 28;
        poomsae = Constantes.KORYO;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        edadInicio = 19;
        edadFin = 30;
        nombreCategoria = "B1";
        positionCinturonInicio = 0;
        positionCinturonFin = 2;
        poomsae = Constantes.POOMSAE1;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "B2";
        positionCinturonInicio = 4;
        positionCinturonFin = 6;
        poomsae = Constantes.POOMSAE2;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "B3";
        positionCinturonInicio = 8;
        positionCinturonFin = 13;
        poomsae = Constantes.POOMSAE4;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "B4";
        positionCinturonInicio = 19;
        positionCinturonFin = 28;
        poomsae = Constantes.KORYO;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        edadInicio = 31;
        edadFin = 40;
        nombreCategoria = "C1";
        positionCinturonInicio = 0;
        positionCinturonFin = 2;
        poomsae = Constantes.POOMSAE1;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "C2";
        positionCinturonInicio = 4;
        positionCinturonFin = 6;
        poomsae = Constantes.POOMSAE2;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "C3";
        positionCinturonInicio = 8;
        positionCinturonFin = 13;
        poomsae = Constantes.POOMSAE4;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "C4";
        positionCinturonInicio = 19;
        positionCinturonFin = 28;
        poomsae = Constantes.KORYO;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        edadInicio = 41;
        edadFin = 50;
        nombreCategoria = "D1";
        positionCinturonInicio = 0;
        positionCinturonFin = 2;
        poomsae = Constantes.POOMSAE1;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "D2";
        positionCinturonInicio = 4;
        positionCinturonFin = 6;
        poomsae = Constantes.POOMSAE2;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "D3";
        positionCinturonInicio = 8;
        positionCinturonFin = 13;
        poomsae = Constantes.POOMSAE4;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "D4";
        positionCinturonInicio = 19;
        positionCinturonFin = 28;
        poomsae = Constantes.KORYO;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        edadInicio = 51;
        edadFin = 99;
        nombreCategoria = "E1";
        positionCinturonInicio = 0;
        positionCinturonFin = 2;
        poomsae = Constantes.POOMSAE1;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "E2";
        positionCinturonInicio = 4;
        positionCinturonFin = 6;
        poomsae = Constantes.POOMSAE2;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "E3";
        positionCinturonInicio = 8;
        positionCinturonFin = 13;
        poomsae = Constantes.POOMSAE4;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "E4";
        positionCinturonInicio = 19;
        positionCinturonFin = 28;
        poomsae = Constantes.KORYO;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasioRoot,
                poomsae, categorias, position);

        categoriaRepository.saveAll(categoriaList);
    }

    private void cargoLista(List<Categoria> categoriaList, int edadInicio, int edadFin, String nombreCategoria,
                            int positionCinturonInicio, int positionCinturonFin, int idGimnasioRoot,
                            String poomsae, List<Boolean> categorias, int position) {
        Categoria categoria = new Categoria(0,edadInicio,edadFin,nombreCategoria,
                positionCinturonInicio,positionCinturonFin,
                cinturonRepository.findByCodigoGimnasioAndPosition(idGimnasioRoot, positionCinturonInicio).getId(),
                cinturonRepository.findByCodigoGimnasioAndPosition(idGimnasioRoot, positionCinturonFin).getId(),
                poomsaeRepository.findByCodigoGimnasioAndNombre(idGimnasioRoot, poomsae).getId(),
                categorias.get(0),categorias.get(1),categorias.get(2),categorias.get(3),position,idGimnasioRoot);
        categoriaList.add(categoria);
    }
}
