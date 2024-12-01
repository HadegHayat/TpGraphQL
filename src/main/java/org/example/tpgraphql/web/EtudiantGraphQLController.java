 /* package org.example.tpgraphql.web;

import org.example.tpgraphql.Repository.CentreRepository;
import org.example.tpgraphql.Repository.EtudiantRepository;
import org.example.tpgraphql.dao.Centre;
import org.example.tpgraphql.dao.Etudiant;
import org.example.tpgraphql.dto.EtudiantDTO;
import org.example.tpgraphql.services.CentreService;
import org.example.tpgraphql.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class EtudiantGraphQLController {

    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private CentreService centreService;

    @QueryMapping
    public List<Etudiant> listEtudiants() {
        return etudiantService.getStudents();
    }

    @QueryMapping
    public Etudiant getEtudiantById(@Argument Long id) {
        return etudiantService.getEtudiant(id);
    }

    @QueryMapping
    public List<Centre> centres() {
        return centreService.centres();
    }

    @QueryMapping
    public Centre getCentreById(@Argument Long id) {
        return centreService.getCentre(id);
    }


    @MutationMapping(name = "addEtudiantFromGraphQLController")
    public Etudiant addEtudiant( EtudiantDTO etudiantDTO) {
        return etudiantService.addEtudiant(etudiantDTO);
    }

    @MutationMapping
    public Etudiant updateEtudiant(@Argument Long id, @Argument EtudiantDTO etudiantDTO) {
        return etudiantService.updateEtudiant(id, etudiantDTO);
    }

    @MutationMapping
    public String deleteEtudiant(@Argument Long id) {
        return etudiantService.deleteEtudiant(id);
    }
}
*/
