package com.example.pokedex.modelos;

import java.util.ArrayList;
//aqui es donde haremos las consultas
public class PokemonRespuesta {
    private ArrayList<Pokemon> results;

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
