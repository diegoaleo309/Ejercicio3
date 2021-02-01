package com.tallercmovil.ejercicio3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tallercmovil.ejercicio3.API.DatoAPI;
import com.tallercmovil.ejercicio3.API.ServicioAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServicioAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServicioAPI pokeapi = retrofit.create(ServicioAPI.class);




        pokemonInfo(pokeapi);








    }


    public void pokemonInfo(ServicioAPI p){


        Call<DatoAPI> pokemonCall = p.getPokemonInfo();
        pokemonCall.enqueue(new Callback<DatoAPI>() {
            @Override
            public void onResponse(Call<DatoAPI> call, Response<DatoAPI> response) {
                // ANY CODE THAT DEPENDS ON THE RESULT OF THE SEARCH HAS TO GO HERE
                DatoAPI foundPoke = response.body();
                // check if the body isn't null
                if (foundPoke != null) {


                    mostrarLista((ArrayList<DatoAPI.PokemonURL>) foundPoke.getResults());


                }
            }

            @Override
            public void onFailure(Call<DatoAPI> call, Throwable t) {
                // TOAST THE FAILURE
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }


    public void mostrarLista(ArrayList<DatoAPI.PokemonURL> listaPokemonURL){


        //for (int i = 0; i < listaPokemon.size(); i++) System.out.println(listaPokemon.get(i).getName());

        ListView lv = findViewById(R.id.lv);

        Adaptador adaptador = new  Adaptador(this, (ArrayList<DatoAPI.PokemonURL>) listaPokemonURL);

        lv.setAdapter(adaptador);

        lv.setOnItemClickListener((adapterView, view, position, id) -> {


            Intent intent;
            Bundle bundle;
            intent = new Intent(this, MainActivity2.class);
            bundle = new Bundle();

            //bundle.putParcelableArrayList("peliculas", peliculas);

            bundle.putInt("id", (int) id);
            intent.putExtras(bundle);


            startActivity(intent);


            //Toast.makeText(MainActivity.this, "Se eligio el elemento con id " + id, Toast.LENGTH_SHORT).show();
            //Pelicula p = (Pelicula) adapterView.getAdapter().getItem((int) id);
        });



        //pokeById();


    }








}



        /*

        if(searchId.equals("")) {





        else {
            Call<Caracteristica> pokeCall = pokeService.getPokemonById(searchId);
            pokeCall.enqueue(new Callback<Caracteristica>() {
                @Override
                public void onResponse(Call<Caracteristica> call, Response<Caracteristica> response) {
                    // ANY CODE THAT DEPENDS ON THE RESULT OF THE SEARCH HAS TO GO HERE
                    Caracteristica foundPoke = response.body();
                    // check if the body isn't null
                    if (foundPoke != null) {
                        textViewName.setText("Name: " + foundPoke.getName());
                        height = Double.parseDouble(foundPoke.getHeight()) / 10;
                        textViewHeight.setText("Height: " + height + "m");
                        weight = Double.parseDouble(foundPoke.getWeight()) / 10;
                        textViewWeight.setText("Weight: " + weight + "kg");
                    }
                }

                @Override
                public void onFailure(Call<Caracteristica> call, Throwable t) {
                    // TOAST THE FAILURE
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }*/

