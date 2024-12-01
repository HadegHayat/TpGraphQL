package org.example.tpgraphql.web;

import jdk.jfr.Category;
import org.example.tpgraphql.dao.Centre;
import org.example.tpgraphql.dao.Etudiant;
import org.example.tpgraphql.dto.EtudiantDTO;
import org.example.tpgraphql.services.CentreService;
import org.example.tpgraphql.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;
@Controller
public class EtudiantCentreController {
    @Autowired
    EtudiantService etudiantService;
    @Autowired
    CentreService centreService;
    @QueryMapping
    public List<Centre> getAllCentres(){
        return centreService.centres();
    }
    @QueryMapping
    public List<Etudiant> getAllEtudiants(){
        return etudiantService.getStudents();
    }
    @QueryMapping
    public Centre getCentre(@Argument int id){
        return centreService.getCentre((long) id);
    }
    @QueryMapping
    public Etudiant getEtudiant(@Argument Long id){
        return etudiantService.getEtudiant(id);
    }
    @MutationMapping(name = "addEtudiantFromCentreController")
    public Etudiant addEtudiant(@Argument EtudiantDTO etudiant) {
        return etudiantService.addEtudiant(etudiant);
    }
    @MutationMapping
    public String suppEtudiant(@Argument Long id){
        return etudiantService.deleteEtudiant(id);
    }
    @MutationMapping
    public Etudiant updateEtudiant(@Argument Long id,@Argument EtudiantDTO etudiant){
        return etudiantService.updateEtudiant(id,etudiant);
    }
    @SubscriptionMapping
    public Flux<Etudiant> etudiantAdded() {
        return etudiantService.getEtudiantAddedPublisher();
    }
}