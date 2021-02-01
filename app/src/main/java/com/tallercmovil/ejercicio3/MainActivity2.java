package com.tallercmovil.ejercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tallercmovil.ejercicio3.API.Pokemon;
import com.tallercmovil.ejercicio3.API.ServicioAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle bundle = getIntent().getExtras();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServicioAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServicioAPI pokeapi = retrofit.create(ServicioAPI.class);





        int id = bundle.getInt("id");

        pokeById(pokeapi, id);
        //System.out.println(id);


        /*
         *Imagen del Pokémon
         * Nombre
         * Experiencia base
         * Altura
         * Peso
         * Tipo
         *
         *
         * URL con la imagen del Pokémon:
         *
         * sprites -> other -> oficial-artwork -> front_default
         *
         *
         *
         * Nombre:
         *
         * species -> name
         *
         *
         *
         * Experiencia base:
         *
         * base_experience
         *
         *
         *
         * Altura:
         *
         * height
         *
         *
         *
         * Peso:
         *
         * weight
         *
         *
         *
         * Tipo(s):
         *
         * types -> 0 -> type -> name
         *
         * opcionalmente: types -> 1 -> type -> name
         *
         *
         *
         */







    }



    public void pokeById(ServicioAPI servicioAPI, int id) {


        Call<Pokemon> pokemonCall =
                servicioAPI.getPokemonById(Integer.toString(id));

        pokemonCall.enqueue(new Callback<Pokemon>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                // ANY CODE THAT DEPENDS ON THE RESULT OF THE SEARCH HAS TO GO HERE
                Pokemon pokemonElegido = response.body();
                // check if the body isn't null
                if (pokemonElegido != null) {

                    TextView tvId = findViewById(R.id.idPokemon);
                    TextView tvPeso = findViewById(R.id.tvPeso);
                    TextView tvExpBase = findViewById(R.id.tvExpBaseString);
                    TextView tvAltura = findViewById(R.id.alturaValor);
                    TextView tvTipo1 = findViewById(R.id.tipo1);
                    TextView tvTipo2 = findViewById(R.id.tipo2);
                    ImageView imagenPokemon = findViewById(R.id.imageView);


                    tvId.setText("#"+ id + "   " +
                            pokemonElegido.getName().substring(0,1).toUpperCase() +
                                pokemonElegido.getName().substring(1).toLowerCase());

                    tvPeso.setText(pokemonElegido.getWeight());

                    tvExpBase.setText(pokemonElegido.geBaseExperience());


                    tvAltura.setText(pokemonElegido.getHeight());

                    tvTipo1.setText(pokemonElegido.getTypes().get(0).getType().getTipo().substring(0,1).toUpperCase()
                            + pokemonElegido.getTypes().get(0).getType().getTipo().substring(1).toLowerCase()
                    );



                    System.out.println();

                    if(pokemonElegido.getTypes().size()==2){

                        tvTipo2.setText(pokemonElegido.getTypes().get(1).getType().getTipo().substring(0,1).toUpperCase()
                                + pokemonElegido.getTypes().get(1).getType().getTipo().substring(1).toLowerCase()
                        );
                    }

                    String imageUri = pokemonElegido.getSprite().getOther().getOfficialArtwork().getFrontDefault();
                    //System.out.println(pokemonElegido.getSprite().getOther().getOfficialArtwork().getFrontDefault());
                    //ImageView ivBasicImage = (ImageView) findViewById(R.id.imageView);
                    Picasso.get().load(imageUri).into(imagenPokemon);


                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Toast.makeText(MainActivity2.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());
            }
        });


    }
}