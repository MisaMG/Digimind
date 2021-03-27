package mendoza.misael.digimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mendoza.misael.digimind.R
import mendoza.misael.digimind.ui.Task
import mendoza.misael.digimind.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val btn_time: Button = root.findViewById(R.id.btn_time)

        btn_time.setOnClickListener{
            val calendar = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{timePicker, hour, minute, ->
             calendar.set(Calendar.HOUR_OF_DAY, hour)
             calendar.set(Calendar.MINUTE, minute)

             btn_time.text = SimpleDateFormat("HH:mm").format(calendar.time)
            }
            TimePickerDialog(root.context, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE), true).show()
        }

        val btn_save = root.findViewById(R.id.btn_save) as Button
        val et_titulo = root.findViewById(R.id.et_text) as EditText
        val checkMonday = root.findViewById(R.id.check_monday) as CheckBox
        val checkTuesday = root.findViewById(R.id.check_tuesday) as CheckBox
        val checkWednesday = root.findViewById(R.id.check_wednesay) as CheckBox
        val checkThursday = root.findViewById(R.id.check_thursday) as CheckBox
        val checkFriday = root.findViewById(R.id.check_friday) as CheckBox
        val checkSaturday = root.findViewById(R.id.check_saturday) as CheckBox
        val checkSunday = root.findViewById(R.id.check_sunday) as CheckBox

        btn_save.setOnClickListener{
            var title = et_titulo.text.toString()
            var time = btn_time.text.toString()
            var days = ArrayList<String>()
            if (checkMonday.isChecked)
                days.add("Monday")
            if (checkTuesday.isChecked)
                days.add("Tuesday")
            if (checkWednesday.isChecked)
                days.add("Wednesday")
            if (checkThursday.isChecked)
                days.add("Thursday")
            if (checkFriday.isChecked)
                days.add("Friday")
            if (checkSaturday.isChecked)
                days.add("Saturday")
            if (checkSunday.isChecked)
                days.add("Sunday")


            var task = Task(title, days, time)

            HomeFragment.tasks.add(task)

            Toast.makeText(root.context, "New task added", Toast.LENGTH_SHORT).show()


        }
        return root
    }
}