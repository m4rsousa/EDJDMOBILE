package com.example.shoppinglist.lists.items
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.TAG
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class AddItemState(
    val name: String = "",
    val qtd: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class AddItemViewModel : ViewModel() {

    var state = mutableStateOf(AddItemState())
        private set


    fun onNameChange(name: String) {
        state.value = state.value.copy(name = name)
    }

    fun onQtdChange(qtd: String) {
        state.value = state.value.copy(qtd = qtd)
    }

    fun addItem(listId: String) {
        val db = Firebase.firestore
        val auth = Firebase.auth
        val currentUser = auth.currentUser
        val userId = currentUser?.uid

        val data = hashMapOf(
            "name" to state.value.name,
            "qtd" to state.value.qtd.toDouble(),
            "owners" to arrayListOf(userId ?: "")
        )

        db.collection("lists")
            .document(listId)
            .collection("items")
            .add(data)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }
}
