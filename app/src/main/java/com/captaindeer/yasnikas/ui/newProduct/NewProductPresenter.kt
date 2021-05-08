package com.captaindeer.yasnikas.ui.newProduct

import android.net.Uri
import android.util.Log
import com.captaindeer.yasnikas.data.remote.models.ShoesModel
import com.captaindeer.yasnikas.ui.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

class NewProductPresenter(private val view: NewProductInterface.View) :
    NewProductInterface.Presenter {

    private var firestore: FirebaseFirestore? = null
    private var storage: FirebaseStorage? = null


    override fun createNewPost(shoe: ShoesModel, file: Uri) {

        storage = FirebaseStorage.getInstance()

        val storageReference = storage!!.reference
        val ref: StorageReference =
            storageReference.child(Constants.COLLECTION_IMAGES + UUID.randomUUID().toString())
        ref.putFile(file).addOnCompleteListener { taskUpload ->
            if (taskUpload.isSuccessful) {
                ref.downloadUrl.addOnSuccessListener { uri ->
                    shoe.image = uri.toString()
                    Log.e("TAG", "Si se consiguio la URL compa ${shoe.image}")
                    val post = hashMapOf(
                        "category" to shoe.category,
                        "color" to shoe.color,
                        "description" to shoe.description,
                        "image" to shoe.image,
                        "model" to shoe.model,
                        "price" to shoe.price,
                        "size" to shoe.size,
                        "stock" to shoe.stock,
                        "sku" to shoe.sku
                    )
                    firestore = FirebaseFirestore.getInstance()
                    firestore!!.collection(Constants.COLLECTION_SHOES).document(shoe.sku).set(post)
                        .addOnCompleteListener { task ->
                            Log.e("TAG", "Si agrego un nuevo producto esta madre")

                        }.addOnFailureListener {
                            Log.e("Tag", "No agrego ni mergas ${it.message.toString()}")
                        }
                }
            }
        }
    }
}