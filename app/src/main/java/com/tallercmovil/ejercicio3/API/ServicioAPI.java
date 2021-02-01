package com.tallercmovil.ejercicio3.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServicioAPI {
    String BASE_URL = "https://pokeapi.co/api/v2/";

    @GET("pokemon/{id}")
    Call<Pokemon> getPokemonById(@Path("id") String id);

    @GET("pokemon?limit=151")
    Call<DatoAPI> getPokemonInfo();


}