package com.example.provafinaluf2;

import android.provider.BaseColumns;

public class PelisContract {

    private PelisContract(){}

    public static class PelisTable implements BaseColumns{
        public static final String TABLE_NAME = "pelicules";
        public static final String COLUMN_TITOL = "titol";
        public static final String COLUMN_GENERE = "genere";
        public static final String COLUMN_DURADA = "durada";
        public static final String COLUMN_NOTA = "nota";
    }
}
