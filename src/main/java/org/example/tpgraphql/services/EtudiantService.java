package org.example.tpgraphql.services;

import org.example.tpgraphql.Repository.CentreRepository;
import org.example.tpgraphql.Repository.EtudiantRepository;
import org.example.tpgraphql.dao.Centre;
import org.example.tpgraphql.dao.Etudiant;
import org.example.tpgraphql.dto.EtudiantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.List;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private CentreRepository centreRepository;

    // Stream de données pour publier les événements d'ajout d'étudiants
    private final Sinks.Many<Etudiant> sink = Sinks.many().multicast().onBackpressureBuffer();

    // Récupérer la liste de tous les étudiants
    public List<Etudiant> getStudents() {
        return etudiantRepository.findAll();
    }

    // Ajouter un étudiant
    public Etudiant addEtudiant(EtudiantDTO etudiantDTO) {
        Centre centre = centreRepository.findById(etudiantDTO.centreId()).orElse(null);

        if (centre != null) {
            Etudiant etudiant = new Etudiant();
            etudiant.setNom(etudiantDTO.nom());
            etudiant.setPrenom(etudiantDTO.prenom());
            etudiant.setGenre(etudiantDTO.genre());
            etudiant.setCentre(centre);

            Etudiant savedEtudiant = etudiantRepository.save(etudiant);
            sink.tryEmitNext(savedEtudiant);
            return savedEtudiant;
        }
        return null; // Retourne null si le centre est introuvable
    }

    // Mettre à jour un étudiant
    public Etudiant updateEtudiant(Long id, EtudiantDTO etudiantDTO) {
        Centre centre = centreRepository.findById(etudiantDTO.centreId()).orElse(null);

        if (centre != null) {
            return etudiantRepository.findById(id).map(etudiant -> {
                etudiant.setNom(etudiantDTO.nom());
                etudiant.setPrenom(etudiantDTO.prenom());
                etudiant.setGenre(etudiantDTO.genre());
                etudiant.setCentre(centre);
                return etudiantRepository.save(etudiant);
            }).orElse(null); // Retourne null si l'étudiant n'existe pas
        }

        return null; // Retourne null si le centre est introuvable
    }

    // Supprimer un étudiant
    public String deleteEtudiant(Long id) {
        if (etudiantRepository.existsById(id)) {
            etudiantRepository.deleteById(id);
            return String.format("L'étudiant %s a été supprimé avec succès !", id);
        }
        return String.format("L'étudiant %s n'existe pas !", id);
    }

    // Récupérer un étudiant par son ID
    public Etudiant getEtudiant(Long id) {
        return etudiantRepository.findById(id).orElse(null);
    }

    // Obtenir un flux des étudiants ajoutés
    public Flux<Etudiant> getEtudiantAddedPublisher() {
        return sink.asFlux();
    }
}

