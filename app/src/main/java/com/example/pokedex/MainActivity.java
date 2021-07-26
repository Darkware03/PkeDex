package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.pokedex.modelos.Pokemon;
import com.example.pokedex.modelos.PokemonRespuesta;
import com.example.pokedex.pokeapi.PokeapiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private static final String TAG = "POKEDEX";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        obtenerDatos();
    }

    private void obtenerDatos(){
        //usaremos la interfaz que acabamos de crear
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<PokemonRespuesta> pokemonRespuestaCall =   service.obtenerListado();

        //con este metodo manejar los resultados en sus metodos internos "enqueue"
        pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            //este metodo se ejecuta cuando llega la respuesta a la consulta que hicimos
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                if (response.isSuccessful()){
                    PokemonRespuesta pokemonRespuesta = response.body();
                    ArrayList<Pokemon> listaPokemon = pokemonRespuesta.getResults();
                    for (int i = 0; i < listaPokemon.size(); i++){
                        Pokemon p = listaPokemon.get(i);
                        Log.i(TAG, "pokemon:" + p.getName());
                    }
                }else {
                    Log.e(TAG, "onResponse:" + response.errorBody());
                }
            }

            //este metodo se ejecuta cuando hay algun problema, ejemplo cuando no tengamos conexion a internet, tiempo de respuesta,etc
            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                Log.e(TAG, "onResponse:" + t.getMessage());
            }
        });
    }
}