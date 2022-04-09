package com.dev.service;

import org.springframework.web.bind.annotation.ResponseBody;
import com.dev.model.Coche;
import com.dev.model.Person;
import com.dev.repository.CocheRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
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

    public Person[]   listaApi() {
         HttpHeaders headers = new HttpHeaders();
         // headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
         HttpEntity <String> entity = new HttpEntity<String>(headers);
         RestTemplate restTemplate = new RestTemplate();
  
         String resourceUrl
           = "https://jsonplaceholder.typicode.com/posts/1/comments";
  
         // Fetching response as Object  
         ResponseEntity<Person[]> products
           = restTemplate.getForEntity(resourceUrl, Person[].class);
  
         return products.getBody();
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