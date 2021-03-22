package mendoza.misael.digimind.ui

import java.io.Serializable

class Carrito : Serializable {
    var recordatorio = ArrayList<Recordatorio>()

    fun agregar(p: Recordatorio): Boolean{
        return recordatorio.add(p)
    }
}