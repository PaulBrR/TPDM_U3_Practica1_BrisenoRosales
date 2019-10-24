package paulbriseno.ittepi.edu.mx.tpdm_u3_practica1_brisenorosales

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    var menulw1 :ListView?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menulw1=findViewById(R.id.menuLW1)
        menulw1?.setOnItemClickListener { adapterView, view, i, l ->
            when(i){
                0->irActivity1()
                1->irActivity2()
            }
        }
    }

    fun irActivity1(){
        var ventan1 =Intent(this,Main2Activity::class.java)
        startActivity(ventan1)

    }
    fun irActivity2(){
        var ventana2=Intent(this,Main3Activity::class.java)
        startActivity(ventana2)

    }
}
