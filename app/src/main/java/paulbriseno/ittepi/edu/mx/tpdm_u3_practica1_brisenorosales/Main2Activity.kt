package paulbriseno.ittepi.edu.mx.tpdm_u3_practica1_brisenorosales

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore

class Main2Activity : AppCompatActivity() {

   var descripcion : EditText ?= null
    var monto : EditText ?= null
    var fechaVencimiento : EditText ?= null
    var pagado : EditText ?= null
    var agregar : Button ?= null
    var regresar :Button?=null
    var lW2 : ListView ?= null

    var baseRemota = FirebaseFirestore.getInstance()
    var registros0 = ArrayList<String>()
    var keys0 = java.util.ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        descripcion = findViewById(R.id.descripcion)
        monto = findViewById(R.id.monto)
        fechaVencimiento = findViewById(R.id.fecha)
        pagado = findViewById(R.id.pagado)
        agregar = findViewById(R.id.agregar)
        regresar=findViewById(R.id.regresar)
        lW2 = findViewById(R.id.listw2)

        agregar?.setOnClickListener{
            var insertar = hashMapOf(
                "descripcion" to descripcion?.text.toString(),
                "monto" to monto?.text.toString().toDouble(),
                "fechaVencimiento" to fechaVencimiento?.text.toString(),
                "pagado" to pagado?.text.toString()
            )
            baseRemota.collection("recibos")
                .add(insertar as Map<String, Any>)
                .addOnSuccessListener {
                    Toast.makeText(this,"Se inserto correctamente",Toast.LENGTH_SHORT)
                        .show()
                }
                .addOnFailureListener {
                    Toast.makeText(this,"Error!. No se inserto"+it.message,Toast.LENGTH_SHORT)
                        .show()
                }
            limpiarTodo()
        }

        regresar?.setOnClickListener {
            finish()
        }

    }
    fun limpiarTodo(){
        descripcion?.setText("")
        monto?.setText("")
        fechaVencimiento?.setText("")
        pagado?.setText("")
    }
}
