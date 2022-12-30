package com.example.easynotes;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Utility {


    static void ShowToast(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }


        static CollectionReference getCollectionReferenceForNotes(){
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            return FirebaseFirestore.getInstance().collection("Notes").document(currentUser.getUid()).collection("mynotes");

        }

}


