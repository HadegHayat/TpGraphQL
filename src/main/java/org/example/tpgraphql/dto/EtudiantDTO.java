package org.example.tpgraphql.dto;

import org.example.tpgraphql.enums.Genre;

public record EtudiantDTO(
        String nom,
        String prenom,
        Genre genre,
        Long centreId
)
{

}
