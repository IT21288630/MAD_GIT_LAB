package com.example.myapplication.modal

class FormDat(private var studentID: String,
              private var year: String,
              private var semester: String,
              private var agreement: Boolean) {

    fun validateStudentID() : ValidationResult{
        return if (studentID.isBlank()){
            ValidationResult.Empty("StudentID is empty")
        }else if (!studentID.startsWith("IT")){
            ValidationResult.Invalid("StudentID should start with 'IT'")
        }else if(studentID.length<10){
            ValidationResult.Invalid("Student ID should have 10 characters")
        }else if(studentID.length>10){
            ValidationResult.Invalid("Student ID should have 10 characters")
        }else {
            ValidationResult.Valid
        }
    }

    fun validateYear() : ValidationResult{
        return if (year.isEmpty()){
            ValidationResult.Empty("Year is empty")
        }else {
            ValidationResult.Valid
        }
    }

    fun validateSemester() : ValidationResult{
        return if (semester.isEmpty()){
            ValidationResult.Empty("Semester is empty")
        }else{
            ValidationResult.Valid
        }
    }

    fun validateAgreement() : ValidationResult{
        return if (!agreement){
            ValidationResult.Empty("You should agree to the terms")
        }else {
            ValidationResult.Valid
        }
    }
}