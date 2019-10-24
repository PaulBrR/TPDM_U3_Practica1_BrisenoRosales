package paulbriseno.ittepi.edu.mx.tpdm_u3_practica1_brisenorosales

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore

class Main3Activity : AppCompatActivity() {
    var listaActuales : ListView?=null

    var baseRemotaActuales = FirebaseFirestore.getInstance()
    var registros0 = ArrayList<String>()
    var keys0 = java.util.ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        listaActuales=findViewById(R.id.listWactuales)
        baseRemotaActuales.collection("recibos")
            .addSnapshotListener { querySnapshot, e ->
                if(e != null){
                    Toast.makeText(this,"Error!. No se pudo hacer consulta", Toast.LENGTH_SHORT)
                        .show()
                    return@addSnapshotListener
                }
                registros0.clear()
                keys0.clear()
                for(document in querySnapshot!!){
                    var llenado = "Descripci0n: ${document.getString("descripcion")}\n" +
                            "Monto: ${document.getDouble("monto")}\n" +
                            "Fecha de vencimiento: ${document.getString("fechaVencimiento")}\n" +
                            "Pagado: ${document.getString("pagado")} \n "

                    registros0.add(llenado)
                    keys0.add(document.id)
                }
                if(registros0.size==0){
                    registros0.add("No hay datos aun para mostrar")
                }
                var adaptador = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,registros0)
                listaActuales?.adapter = adaptador
            } //Collection

        listaActuales?.setOnItemClickListener { adapterView, view, position, l ->
            if(keys0.size==0){
                return@setOnItemClickListener
            }
            else{
                AlertDialog.Builder(this)
                    .setTitle("!Atencion")
                    .setMessage("Que desea hacer con\n ${registros0.get(position)} ")
                    .setPositiveButton("Eliminar"){ dialogInterface, i ->
                        baseRemotaActuales.collection("recibos")
                            .document(keys0.get(position))
                            .delete()
                            .addOnSuccessListener {
                                Toast.makeText(this,"Eliminado correctamente!",Toast.LENGTH_SHORT)
                                    .show()
                            }
                            .addOnFailureListener {
                                Toast.makeText(this,"Eliminado erroneamente",Toast.LENGTH_SHORT)
                                    .show()
                            }
                    }
                    .setNegativeButton("Actualizar"){dialogInterface, i ->
                        var irventana = Intent(this,Main4Activity::class.java)
                        irventana.putExtra("id",keys0.get(position))
                        startActivity(irventana)
                    }
                    .setNeutralButton("Cancelar"){ dialogInterface, i ->
                    }.show()
            }
        }//ListenerLw2

    }
}
