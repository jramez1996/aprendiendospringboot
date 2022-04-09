package com.dev.service;

import org.springframework.web.bind.annotation.ResponseBody;
import com.dev.model.Coche;
import com.dev.repository.CocheRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service 
public class CocheService {
    @Autowired
    CocheRepository cocheRepository;
    public List<Coche>   lista() {
        return cocheRepository.listaProcedure();
    }
    /*
    public Optional<Coche> getById(int id){
        return cocheRepository.idProcedure(id);
    }

    public List<Coche> getByMarca(String marca){
        return cocheRepository.marcaProcedure(marca);
    }

    public void saveProcedure(Coche coche){
        cocheRepository.saveProcedure(coche.getMarca(), coche.getModelo(), coche.getAnyo(), coche.getKm());
    }

    public float mediaKm(){
        return cocheRepository.mediaKm();
    }

    public void borrarProcedure(int id){
        cocheRepository.borrarProcedure(id);
    }*/
}