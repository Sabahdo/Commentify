package com.example.commentify;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.commentify.model.Comment;
import com.example.commentify.model.CommentDB;

import java.util.List;

public class CommentViewmodel extends AndroidViewModel {

    //invoer moet direct toegevoegd worden in database
    private final LiveData<List<Comment>> COMMENTS;

    //data komt uit database
    private CommentDB database;

    public CommentViewmodel(@NonNull Application application) {
        super(application);
        database = CommentDB.getsharedInstance(application);
        COMMENTS = database.getCommentDAO().getAllComments();
    }
    public LiveData<List<Comment>> getCOMMENTS(){
        return COMMENTS;
    }

    public void insertComment(Comment c){
        CommentDB.databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                database.getCommentDAO().insertComment(c);
            }
        });
    }

}
