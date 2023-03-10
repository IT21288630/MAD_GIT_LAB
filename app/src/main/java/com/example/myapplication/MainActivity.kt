package com.example.myapplication

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.example.myapplication.modal.FormDat
import com.example.myapplication.modal.FormData
import com.example.myapplication.modal.ValidationResult

class MainActivity : AppCompatActivity() {

    lateinit var studentID : EditText
    lateinit var year : Spinner
    lateinit var semester : Spinner
    lateinit var agree : CheckBox
    lateinit var submit : Button
    lateinit var cancel : Button
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentID = findViewById(R.id.stdID)
        year = findViewById(R.id.spnrYear)
        semester = findViewById(R.id.spnrSemester)
        agree = findViewById(R.id.agreement)
        submit = findViewById(R.id.submit)
        cancel = findViewById(R.id.cancel)
    }

    override fun onResume() {
        super.onResume()

        submit.setOnClickListener(View.OnClickListener {

            count = 0

            val formDat = FormDat(studentID.text.toString(), year.selectedItem.toString(), semester.selectedItem.toString(), agree.isChecked)

            val studentIDValidation = formDat.validateStudentID()
            val yearValidation = formDat.validateYear()
            val semesterValidation = formDat.validateSemester()
            val agreementValidation = formDat.validateAgreement()

            when(studentIDValidation){
                is ValidationResult.Valid -> {
                    count++
                }
                is ValidationResult.Invalid -> {
                    studentID.error = studentIDValidation.errorMessage
                }
                is ValidationResult.Empty -> {
                    studentID.error = studentIDValidation.errorMessage
                }
            }

            when(yearValidation){
                is ValidationResult.Valid -> {
                    count++
                }
                is ValidationResult.Invalid -> {
                    val errTV : TextView = year.selectedView as TextView
                    errTV.error = ""
                    errTV.text = yearValidation.errorMessage
                }
                is ValidationResult.Empty -> {
                    val errTV : TextView = year.selectedView as TextView
                    errTV.error = ""
                    errTV.text = yearValidation.errorMessage
                }
            }

            when(semesterValidation){
                is ValidationResult.Valid -> {
                    count++
                }
                is ValidationResult.Invalid -> {
                    val errTV : TextView = semester.selectedView as TextView
                    errTV.error = ""
                    errTV.text = semesterValidation.errorMessage
                }
                is ValidationResult.Empty -> {
                    val errTV : TextView = semester.selectedView as TextView
                    errTV.error = ""
                    errTV.text = semesterValidation.errorMessage
                }
            }

            when(agreementValidation){
                is ValidationResult.Valid -> {
                    count++
                }
                is ValidationResult.Empty -> {

                }
                is ValidationResult.Invalid -> {
                    displayAlert("Error", agreementValidation.errorMessage)
                }
            }

            if (count == 4){
                displayAlert("Success", "You have successfully registered")

                FormData(studentID.text.toString(), Integer.parseInt(year.selectedItem.toString()), semester.selectedItem.toString(), agree.isChecked)
            }
        })
    }

    fun displayAlert(title:String, message:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK")
        {
                dialog, which -> {}        // Do something when the "OK" button is clicked     }
        }
        val dialog = builder.create()
        dialog.show()
    }
}