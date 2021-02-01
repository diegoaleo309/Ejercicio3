package com.tallercmovil.ejercicio3.API;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pokemon {

    @SerializedName("name")
    private String name;
    @SerializedName("height")
    private String height;
    @SerializedName("weight")
    private String weight;
    @SerializedName("base_experience")
    private String base_experience;
    @SerializedName("types")
    private List<Tipos> types;
    @SerializedName("sprites")
    private Sprite sprites;



    public Pokemon() {

    }

    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String geBaseExperience() {
        return base_experience;
    }

    public List<Tipos> getTypes(){ return types;}

    public Sprite getSprite(){return sprites;}



    public class Tipos{

        @SerializedName("slot")
        private String slot;

        @SerializedName("type")
        private Tipo type;

        public Tipo getType () {return type;}
        public String getSlot () {return slot;}


        public class Tipo{

            @SerializedName("name")
            private String name;

            @SerializedName("url")
            private String url;


            public String getTipo(){return name;}

        }

    }


    public class Sprite{

        @SerializedName("other")
        private Other other;

        public Other getOther(){return other;}



        public class Other{

            @SerializedName("official-artwork")
            private OfficialArtwork artwork;

            public OfficialArtwork getOfficialArtwork(){return artwork;}



            public class OfficialArtwork{

                @SerializedName("front_default")
                private String front_default;

                public String getFrontDefault(){return front_default;}


            }
        }

    }



}