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

    public void cargasCintPoomCat(int idGimnasio) {
        cargaCinturones(idGimnasio);
        cargaPoomsaes(idGimnasio);
        cargaCategorias(idGimnasio);
    }

    public void eliminacionesCatPoomCint (int idGimnasio) {
        eliminarCategorias(idGimnasio);
        eliminarPoomsaes(idGimnasio);
        eliminarCinturones(idGimnasio);
    }

    public void eliminarCategorias (int idGimnasio) {
        List<Categoria> categoriaList = categoriaRepository.findByCodigoGimnasio(idGimnasio);
        categoriaRepository.deleteAll(categoriaList);
    }

    public void eliminarPoomsaes (int idGimnasio) {
        List<Poomsae> poomsaeList = poomsaeRepository.findByCodigoGimnasio(idGimnasio);
        poomsaeRepository.deleteAll(poomsaeList);
    }

    public void eliminarCinturones (int idGimnasio){
        List<Cinturon> cinturonList = cinturonRepository.findByCodigoGimnasio(idGimnasio);
        cinturonRepository.deleteAll(cinturonList);
    }

    private void cargaCinturones(int idGimnasio) {
        List<Cinturon> cinturonList = new ArrayList<>();
        cinturonList.add(new Cinturon(Constantes.BLANCO, 0, idGimnasio));
        cinturonList.add(new Cinturon("Blanco Amarillo", 1, idGimnasio));
        cinturonList.add(new Cinturon("Amarillo", 2, idGimnasio));
        cinturonList.add(new Cinturon("Amarillo Naranja", 3, idGimnasio));
        cinturonList.add(new Cinturon("Naranja", 4, idGimnasio));
        cinturonList.add(new Cinturon("Naranja Verde", 5, idGimnasio));
        cinturonList.add(new Cinturon("Verde", 6, idGimnasio));
        cinturonList.add(new Cinturon("Verde Azul", 7, idGimnasio));
        cinturonList.add(new Cinturon("Azul", 8, idGimnasio));
        cinturonList.add(new Cinturon("Azul Rojo", 9, idGimnasio));
        cinturonList.add(new Cinturon("Azul Marrón", 10, idGimnasio));
        cinturonList.add(new Cinturon("Marrón", 11, idGimnasio));
        cinturonList.add(new Cinturon("Marrón Rojo", 12, idGimnasio));
        cinturonList.add(new Cinturon("Rojo", 13, idGimnasio));
        cinturonList.add(new Cinturon("Rojo Negro", 14, idGimnasio));
        cinturonList.add(new Cinturon("Rojo Negro 1º PUM", 15, idGimnasio));
        cinturonList.add(new Cinturon("Rojo Negro 2º PUM", 16, idGimnasio));
        cinturonList.add(new Cinturon("Rojo Negro 3º PUM", 17, idGimnasio));
        cinturonList.add(new Cinturon("Rojo Negro 4º PUM", 18, idGimnasio));
        cinturonList.add(new Cinturon("Negro 1º DAN", 19, idGimnasio));
        cinturonList.add(new Cinturon("Negro 2º DAN", 20, idGimnasio));
        cinturonList.add(new Cinturon("Negro 3º DAN", 21, idGimnasio));
        cinturonList.add(new Cinturon("Negro 4º DAN", 22, idGimnasio));
        cinturonList.add(new Cinturon("Negro 5º DAN", 23, idGimnasio));
        cinturonList.add(new Cinturon("Negro 6º DAN", 24, idGimnasio));
        cinturonList.add(new Cinturon("Negro 7º DAN", 25, idGimnasio));
        cinturonList.add(new Cinturon("Negro 8º DAN", 26, idGimnasio));
        cinturonList.add(new Cinturon("Negro 9º DAN", 27, idGimnasio));
        cinturonList.add(new Cinturon("Negro 10º DAN", 28, idGimnasio));
        cinturonRepository.saveAll(cinturonList);
    }

    private void cargaPoomsaes(int idGimnasio) {
        List<Poomsae> poomsaeList = new ArrayList<>();
        poomsaeList.add(new Poomsae(Constantes.KICHO,0,idGimnasio));
        poomsaeList.add(new Poomsae(Constantes.POOMSAE1,1,idGimnasio));
        poomsaeList.add(new Poomsae(Constantes.POOMSAE2,2,idGimnasio));
        poomsaeList.add(new Poomsae(Constantes.POOMSAE3,3,idGimnasio));
        poomsaeList.add(new Poomsae(Constantes.POOMSAE4,4,idGimnasio));
        poomsaeList.add(new Poomsae(Constantes.POOMSAE5,5,idGimnasio));
        poomsaeList.add(new Poomsae(Constantes.POOMSAE6,6,idGimnasio));
        poomsaeList.add(new Poomsae(Constantes.POOMSAE7,7,idGimnasio));
        poomsaeList.add(new Poomsae(Constantes.POOMSAE8,8,idGimnasio));
        poomsaeList.add(new Poomsae(Constantes.KORYO,9,idGimnasio));
        poomsaeList.add(new Poomsae(Constantes.KUMGANG,10,idGimnasio));
        poomsaeList.add(new Poomsae(Constantes.TAEBEK,11,idGimnasio));
        poomsaeList.add(new Poomsae(Constantes.PYONGWON,12,idGimnasio));
        poomsaeList.add(new Poomsae(Constantes.SYPCCHIN,13,idGimnasio));
        poomsaeList.add(new Poomsae(Constantes.CHITAE,14,idGimnasio));
        poomsaeList.add(new Poomsae(Constantes.CHUNGKWON,15,idGimnasio));
        poomsaeList.add(new Poomsae(Constantes.JANSU,16,idGimnasio));
        poomsaeList.add(new Poomsae(Constantes.ILIO,17,idGimnasio));
        poomsaeList.add(new Poomsae(Constantes.INCLUSIVO,18,idGimnasio));
        poomsaeRepository.saveAll(poomsaeList);
    }


    private void cargaCategorias(int idGimnasio) {

        List<Categoria> categoriaList = new ArrayList<>();
        int position = 0;
        int edadInicio = 0;
        int edadFin = 0;
        String nombreCategoria = Constantes.INCLUSIVO;
        int positionCinturonInicio = 0;
        int positionCinturonFin = 28;
        String poomsae = Constantes.INCLUSIVO;
        List<Boolean> categorias = Arrays.asList(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        edadInicio = 3;
        edadFin = 3;
        nombreCategoria = "A";
        //positionCinturonInicio = 0;
        positionCinturonFin = 18;
        poomsae = Constantes.KICHO;
        categorias = Arrays.asList(Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE);
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        edadInicio = 4;
        edadFin = 4;
        nombreCategoria = "B";
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        edadInicio = 5;
        edadFin = 5;
        nombreCategoria = "C";
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        edadInicio = 6;
        edadFin = 6;
        nombreCategoria = "D";
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        edadInicio = 7;
        edadFin = 7;
        nombreCategoria = "E";
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        edadInicio = 8;
        edadFin = 8;
        nombreCategoria = "F";
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        edadInicio = 7;
        edadFin = 7;
        nombreCategoria = "A1";
        //positionCinturonInicio = 0;
        positionCinturonFin = 2;
        poomsae = Constantes.POOMSAE1;
        categorias = Arrays.asList(Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE);
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "A2";
        positionCinturonInicio = 3;
        positionCinturonFin = 5;
        poomsae = Constantes.POOMSAE1;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "A3";
        positionCinturonInicio = 6;
        positionCinturonFin = 18;
        poomsae = Constantes.POOMSAE1;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        edadInicio = 8;
        edadFin = 9;
        nombreCategoria = "B1";
        positionCinturonInicio = 0;
        positionCinturonFin = 3;
        poomsae = Constantes.POOMSAE1;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "B2";
        positionCinturonInicio = 4;
        positionCinturonFin = 7;
        poomsae = Constantes.POOMSAE2;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "B3";
        positionCinturonInicio = 8;
        positionCinturonFin = 18;
        poomsae = Constantes.POOMSAE3;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        edadInicio = 10;
        edadFin = 11;
        nombreCategoria = "C1";
        positionCinturonInicio = 0;
        positionCinturonFin = 3;
        poomsae = Constantes.POOMSAE1;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "C2";
        positionCinturonInicio = 4;
        positionCinturonFin = 7;
        poomsae = Constantes.POOMSAE2;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "C3";
        positionCinturonInicio = 8;
        positionCinturonFin = 13;
        poomsae = Constantes.POOMSAE3;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "C4";
        positionCinturonInicio = 14;
        positionCinturonFin = 18;
        poomsae = Constantes.KORYO;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        edadInicio = 12;
        edadFin = 13;
        nombreCategoria = "D1";
        positionCinturonInicio = 0;
        positionCinturonFin = 3;
        poomsae = Constantes.POOMSAE1;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "D2";
        positionCinturonInicio = 4;
        positionCinturonFin = 7;
        poomsae = Constantes.POOMSAE2;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "D3";
        positionCinturonInicio = 8;
        positionCinturonFin = 13;
        poomsae = Constantes.POOMSAE5;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "D4";
        positionCinturonInicio = 14;
        positionCinturonFin = 18;
        poomsae = Constantes.KORYO;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        edadInicio = 14;
        edadFin = 15;
        nombreCategoria = "E1";
        positionCinturonInicio = 0;
        positionCinturonFin = 3;
        poomsae = Constantes.POOMSAE1;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "E2";
        positionCinturonInicio = 4;
        positionCinturonFin = 7;
        poomsae = Constantes.POOMSAE2;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "E3";
        positionCinturonInicio = 8;
        positionCinturonFin = 13;
        poomsae = Constantes.POOMSAE5;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "E4";
        positionCinturonInicio = 14;
        positionCinturonFin = 18;
        poomsae = Constantes.KORYO;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        edadInicio = 16;
        edadFin = 18;
        nombreCategoria = "A1";
        positionCinturonInicio = 0;
        positionCinturonFin = 2;
        poomsae = Constantes.POOMSAE1;
        categorias = Arrays.asList(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE);
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "A2";
        positionCinturonInicio = 4;
        positionCinturonFin = 6;
        poomsae = Constantes.POOMSAE2;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "A3";
        positionCinturonInicio = 8;
        positionCinturonFin = 13;
        poomsae = Constantes.POOMSAE4;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "A4";
        positionCinturonInicio = 19;
        positionCinturonFin = 28;
        poomsae = Constantes.KORYO;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        edadInicio = 19;
        edadFin = 30;
        nombreCategoria = "B1";
        positionCinturonInicio = 0;
        positionCinturonFin = 2;
        poomsae = Constantes.POOMSAE1;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "B2";
        positionCinturonInicio = 4;
        positionCinturonFin = 6;
        poomsae = Constantes.POOMSAE2;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "B3";
        positionCinturonInicio = 8;
        positionCinturonFin = 13;
        poomsae = Constantes.POOMSAE4;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "B4";
        positionCinturonInicio = 19;
        positionCinturonFin = 28;
        poomsae = Constantes.KORYO;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        edadInicio = 31;
        edadFin = 40;
        nombreCategoria = "C1";
        positionCinturonInicio = 0;
        positionCinturonFin = 2;
        poomsae = Constantes.POOMSAE1;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "C2";
        positionCinturonInicio = 4;
        positionCinturonFin = 6;
        poomsae = Constantes.POOMSAE2;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "C3";
        positionCinturonInicio = 8;
        positionCinturonFin = 13;
        poomsae = Constantes.POOMSAE4;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "C4";
        positionCinturonInicio = 19;
        positionCinturonFin = 28;
        poomsae = Constantes.KORYO;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        edadInicio = 41;
        edadFin = 50;
        nombreCategoria = "D1";
        positionCinturonInicio = 0;
        positionCinturonFin = 2;
        poomsae = Constantes.POOMSAE1;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "D2";
        positionCinturonInicio = 4;
        positionCinturonFin = 6;
        poomsae = Constantes.POOMSAE2;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "D3";
        positionCinturonInicio = 8;
        positionCinturonFin = 13;
        poomsae = Constantes.POOMSAE4;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "D4";
        positionCinturonInicio = 19;
        positionCinturonFin = 28;
        poomsae = Constantes.KORYO;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        edadInicio = 51;
        edadFin = 99;
        nombreCategoria = "E1";
        positionCinturonInicio = 0;
        positionCinturonFin = 2;
        poomsae = Constantes.POOMSAE1;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "E2";
        positionCinturonInicio = 4;
        positionCinturonFin = 6;
        poomsae = Constantes.POOMSAE2;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "E3";
        positionCinturonInicio = 8;
        positionCinturonFin = 13;
        poomsae = Constantes.POOMSAE4;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);
        position++;

        nombreCategoria = "E4";
        positionCinturonInicio = 19;
        positionCinturonFin = 28;
        poomsae = Constantes.KORYO;
        cargoLista(categoriaList, edadInicio, edadFin, nombreCategoria, positionCinturonInicio, positionCinturonFin, idGimnasio,
                poomsae, categorias, position);

        categoriaRepository.saveAll(categoriaList);
    }

    private void cargoLista(List<Categoria> categoriaList, int edadInicio, int edadFin, String nombreCategoria,
                            int positionCinturonInicio, int positionCinturonFin, int idGimnasio,
                            String poomsae, List<Boolean> categorias, int position) {
        Categoria categoria = new Categoria(0,edadInicio,edadFin,nombreCategoria,
                positionCinturonInicio,positionCinturonFin,
                cinturonRepository.findByCodigoGimnasioAndPosition(idGimnasio, positionCinturonInicio).getId(),
                cinturonRepository.findByCodigoGimnasioAndPosition(idGimnasio, positionCinturonFin).getId(),
                poomsaeRepository.findByCodigoGimnasioAndNombre(idGimnasio, poomsae).getId(),
                categorias.get(0),categorias.get(1),categorias.get(2),categorias.get(3),position,idGimnasio);
        categoriaList.add(categoria);
    }
}
