package com.example.commentify.model;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//achterliggend een databank maken
@Database(version = 1, entities = {Comment.class})
// roomdatabase wordt door het systeem ingevuld
public abstract class CommentDB extends RoomDatabase {


    //ik wil maar op 1 database verbinding werken per keer
    private static CommentDB sharedInstance;

    public static CommentDB getsharedInstance(Context context){

        if(sharedInstance == null)
            createDB(context);

            return sharedInstance;
    }
    // aanmaak database, via builder
    private static void createDB(Context context){
        // hieronder geeft je de file een naam, deze zal gebruikt worden
        sharedInstance = Room.databaseBuilder(context,CommentDB.class,"comment_db").build();

    }


    //verwijzing naar de dao, databank kan alle acties uitvoeren van dao
    public abstract CommentDAO getCommentDAO();

    // kijk dit is de ruimte die je achterliggend nog over hebt, om eventueel andere zaken op uit te voeren
    public static ExecutorService databaseExecutor = Executors.newFixedThreadPool(4);

}
