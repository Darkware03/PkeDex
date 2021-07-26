package com.example.pokedex.pokeapi;

import com.example.pokedex.modelos.PokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeapiService {
    //con notacion le indicamos que accion hara, en este caso es get
     @GET("pokemon")
    Call<PokemonRespuesta> obtenerListado();
}
