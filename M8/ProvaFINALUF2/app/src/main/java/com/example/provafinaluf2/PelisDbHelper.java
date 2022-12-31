package com.example.provafinaluf2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.provafinaluf2.PelisContract.*;

public class PelisDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pelicules.db";
    private static final int DATABASE_VERSION = 1;

    public PelisDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_COCHE_TABLE = "CREATE TABLE " +

                PelisTable.TABLE_NAME + " ( " +
                PelisTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PelisTable.COLUMN_TITOL + " TEXT, " +
                PelisTable.COLUMN_GENERE + " TEXT, " +
                PelisTable.COLUMN_DURADA + " TEXT, " +
                PelisTable.COLUMN_NOTA + " TEXT)";

        db.execSQL(SQL_CREATE_COCHE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PelisTable.TABLE_NAME);
        onCreate(db);
    }

    public boolean insertarPeli(Pelis PeliR) {



        List<String> PelisList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT " + PelisTable.COLUMN_TITOL +" FROM " + PelisTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                String peli = new String();
                peli = (c.getString(c.getColumnIndex(PelisTable.COLUMN_TITOL)));
                PelisList.add(peli);
            } while (c.moveToNext());
        }
        c.close();

        if (!(PelisList.contains(PeliR.getTitol()))){
            ContentValues cv = new ContentValues();
            cv.put(PelisTable.COLUMN_TITOL, PeliR.getTitol());
            cv.put(PelisTable.COLUMN_GENERE, PeliR.getGenere());
            cv.put(PelisTable.COLUMN_DURADA, PeliR.getDurada());
            cv.put(PelisTable.COLUMN_NOTA, PeliR.getNota());
            db.insert(PelisTable.TABLE_NAME, null, cv);
            db.close();
            return true;
        }else{
            System.out.println("YA EXISTEEEEEEEEEEEEEEEEEEEEEEEEEEEEe");
            return false;
        }
    }

    public List<Pelis> getAllPelis() {

        List<Pelis> PelisList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + PelisTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Pelis Peli = new Pelis();
                Peli.setTitol(c.getString(c.getColumnIndex(PelisTable.COLUMN_TITOL)));
                Peli.setGenere(c.getString(c.getColumnIndex(PelisTable.COLUMN_GENERE)));
                Peli.setDurada(c.getString(c.getColumnIndex(PelisTable.COLUMN_DURADA)));
                Peli.setNota(c.getString(c.getColumnIndex(PelisTable.COLUMN_NOTA)));
                PelisList.add(Peli);
            } while (c.moveToNext());
        }
        c.close();
        return PelisList;
    }

    public void deletePeli(String titol) {
        SQLiteDatabase db = this.getReadableDatabase();
        final String SqlDelete = "DELETE FROM " + PelisTable.TABLE_NAME + " WHERE " + PelisTable.COLUMN_TITOL + " = '" + titol + "'";
        db.execSQL(SqlDelete);
    }

    public void modificarPeli(String titol, String genere, String durada, String nota) {
        SQLiteDatabase db = this.getReadableDatabase();
        System.out.println(titol+"--------------------------------------------");
        final String SqlUpdate = "UPDATE " + PelisTable.TABLE_NAME + " SET " + PelisTable.COLUMN_TITOL + " = '" + titol + "', " + PelisTable.COLUMN_GENERE + " = '" + genere + "', " + PelisTable.COLUMN_DURADA + " = '" + durada + "', " + PelisTable.COLUMN_NOTA + " = '" + nota + "' WHERE " + PelisTable.COLUMN_TITOL + " = '" + titol + "'";
        db.execSQL(SqlUpdate);
    }

    public String consultarPeli(String genere){
        System.out.println(genere+"-----------------------------------------");
        String PeliConsulta="";
        Pelis peli = new Pelis();

        SQLiteDatabase db = this.getReadableDatabase();
        final String SqlConsulta = "SELECT * FROM "+PelisTable.TABLE_NAME+" WHERE "+PelisTable.COLUMN_GENERE+" = '"+genere+"'";
        Cursor c = db.rawQuery(SqlConsulta, null);
        if (c.moveToFirst()) {
            do {
                peli.setTitol(c.getString(c.getColumnIndex(PelisTable.COLUMN_TITOL)));
                peli.setGenere(c.getString(c.getColumnIndex(PelisTable.COLUMN_GENERE)));
                peli.setDurada(c.getString(c.getColumnIndex(PelisTable.COLUMN_DURADA)));
                peli.setNota(c.getString(c.getColumnIndex(PelisTable.COLUMN_NOTA)));
                PeliConsulta=peli.toString();
            } while (c.moveToNext());
        }
        c.close();
        return PeliConsulta;
    }
}
