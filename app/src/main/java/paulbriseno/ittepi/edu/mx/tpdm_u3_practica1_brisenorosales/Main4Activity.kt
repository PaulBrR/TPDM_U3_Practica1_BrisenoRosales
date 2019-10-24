package paulbriseno.ittepi.edu.mx.tpdm_u3_practica1_brisenorosales


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class Main4Activity : AppCompatActivity() {
    var descripcion : EditText?= null
    var monto : EditText?= null
    var fechaVencimiento : EditText?= null
    var pagado : EditText?= null
    var actualizar : Button?= null
    var regresar : Button?=null

    var baseRemotaActualizar =FirebaseFirestore.getInstance()
    var id =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        descripcion = findViewById(R.id.descripcionactualizar)
        monto = findViewById(R.id.montoctualizar)
        fechaVencimiento = findViewById(R.id.fechactualizar)
        pagado = findViewById(R.id.pagadoctualizar)
        actualizar = findViewById(R.id.actualizar)
        regresar=findViewById(R.id.regresarctualizar)

        id = intent.extras?.getString("id")!!


        baseRemotaActualizar.collection("recibos")
            .document(id)
            .get()
            .addOnSuccessListener {
                descripcion?.setText(it.getString("descripcion"))
                monto?.setText(it.getDouble("monto").toString())
                fechaVencimiento?.setText(it.getString("fechaVencimiento"))
                pagado?.setText(it.getString("pagado"))
            }
            .addOnFailureListener {
                descripcion?.setText("NULL")
                monto?.setText("NULL")
                fechaVencimiento?.setText("NULL")
                pagado?.setText("NULL")

                descripcion?.isEnabled = false
                monto?.isEnabled = false
                fechaVencimiento?.isEnabled = false
                pagado?.isEnabled = false
            }

        actualizar?.setOnClickListener {
            var act = hashMapOf(
                "descripcion" to descripcion?.text.toString(),
                "monto" to monto?.text.toString().toDouble(),
                "fechaVencimiento" to fechaVencimiento?.text.toString(),
                "pagado" to pagado?.text.toString()
            )
            baseRemotaActualizar.collection("recibos")
                .document(id)
                .set(act as Map<String, Any>)
                .addOnSuccessListener {
                    limpiarTodo()
                    Toast.makeText(this,"actualizo correctamente",Toast.LENGTH_SHORT)
                        .show()
                }
                .addOnFailureListener {
                    Toast.makeText(this,"Error!. \n No actualizo"+it.message,Toast.LENGTH_SHORT)
                        .show()
                }
        }//Listener Actualizar

        regresar?.setOnClickListener {
            finish() }



    }
    fun limpiarTodo(){
        descripcion?.setText("")
        monto?.setText("")
        fechaVencimiento?.setText("")
        pagado?.setText("")
    }
}
