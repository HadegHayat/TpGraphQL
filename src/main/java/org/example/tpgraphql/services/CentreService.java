package org.example.tpgraphql.services;


import org.example.tpgraphql.Repository.CentreRepository;
import org.example.tpgraphql.dao.Centre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CentreService {

    @Autowired
    private CentreRepository centreRepository;

    // Récupérer tous les centres
    public List<Centre> centres() {
        return centreRepository.findAll();
    }

    // Récupérer un centre par son ID
    public Centre getCentre(Long id) {
        return centreRepository.findById(id).orElse(null);
    }

    // Ajouter un nouveau centre
    public Centre addCentre(Centre centre) {
        return centreRepository.save(centre);
    }

    // Mettre à jour un centre
    public Centre updateCentre(Long id, Centre centreDetails) {
        return centreRepository.findById(id).map(centre -> {
            centre.setNom(centreDetails.getNom());
            centre.setAdresse(centreDetails.getAdresse());
            return centreRepository.save(centre);
        }).orElse(null); // Retourne null si le centre n'existe pas
    }

    // Supprimer un centre par son ID
    public String deleteCentre(Long id) {
        if (centreRepository.existsById(id)) {
            centreRepository.deleteById(id);
            return String.format("Le centre %s a été supprimé avec succès !", id);
        }
        return String.format("Le centre %s n'existe pas !", id);
    }
}

