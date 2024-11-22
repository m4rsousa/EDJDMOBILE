package com.example.shoppinglist.lists

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.TAG
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class AddListState(
    val name: String = "",
    val isLoading : Boolean = false,
    val error:String?=null
)
class AddListViewModel : ViewModel(){
    var state = mutableStateOf(AddListState())
        private set

    fun onNameChange(name: String) {
        state.value = state.value.copy(name = name)
    }

    fun addList(){
        val db = Firebase.firestore

        val auth = Firebase.auth
        val currentUser = auth.currentUser
        val userId = currentUser?.uid
        val data = hashMapOf(
            "name" to state.value.name,
            "owners" to arrayListOf(userId?:"")
        )
        db.collection("com/example/shoppinglist/lists")
            .add(data)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

    }
}