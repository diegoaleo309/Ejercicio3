package com.tallercmovil.ejercicio3.API;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DatoAPI {

    @SerializedName("results")
    private List<PokemonURL> results = null;


    public List<PokemonURL> getResults() {
        return results;
    }

    public void setResults(List<PokemonURL> results) {
        this.results = results;
    }



    public class PokemonURL {

       /* @SerializedName("url")
        private String url;*/
        @SerializedName("name")
        private String name;

        /*public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }*/

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}
