package org.example.tpgraphql.Repository;

import org.example.tpgraphql.dao.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
}
