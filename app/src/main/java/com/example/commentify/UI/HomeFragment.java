package com.example.commentify.UI;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.commentify.CommentViewmodel;
import com.example.commentify.R;
import com.example.commentify.Util.CommentAdaptor;
import com.example.commentify.model.Comment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    //field maken voor onze fragment
    private FragmentActivity myContext;
    private EditText userET;
    private EditText contentET;
    private Button btnPost;
    private RecyclerView commentsRV;
    private CommentViewmodel model;
    private View.OnClickListener postCommentListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String user = userET.getText().toString();
            String content = contentET.getText().toString();
            if(user.isEmpty() || content.isEmpty()){
                return;
            }
            Comment c = new Comment(user, content);
            model.insertComment(c);

        }
    };

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }
    //waar is het fragment in geplaatst
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myContext = (FragmentActivity) context;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =   inflater.inflate(R.layout.fragment_home, container, false);
        model = new ViewModelProvider(myContext).get(CommentViewmodel.class);

        userET = rootView.findViewById(R.id.et_username);
        contentET = rootView.findViewById(R.id.et_content);
        btnPost = rootView.findViewById(R.id.btn_post);
        commentsRV = rootView.findViewById(R.id.rv_comments);

        CommentAdaptor adaptor = new CommentAdaptor();
        commentsRV.setAdapter(adaptor);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(myContext, RecyclerView.VERTICAL, false);
        commentsRV.setLayoutManager(layoutManager);

        model.getCOMMENTS().observe(myContext, new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {
                adaptor.addItems(comments);
                adaptor.notifyDataSetChanged();
            }
        });

        btnPost.setOnClickListener(postCommentListener);

        return rootView;


    }
}
