package com.example.studentmanagernew

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class StudentDetailFragment : Fragment() {

    private lateinit var editTextName: EditText
    private lateinit var editTextStudentID: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_student_detail, container, false)
        editTextName = view.findViewById(R.id.editTextName)
        editTextStudentID = view.findViewById(R.id.editTextStudentID)
        val buttonSave = view.findViewById<Button>(R.id.buttonSave)

        val student = arguments?.getParcelable<Student>("student")
        if (student != null) {
            editTextName.setText(student.name)
            editTextStudentID.setText(student.id)
        }

        buttonSave.setOnClickListener {
            val name = editTextName.text.toString()
            val id = editTextStudentID.text.toString()
            val newStudent = Student(name, id)

            val navController = findNavController()
            navController.previousBackStackEntry?.savedStateHandle?.set("student", newStudent)
            navController.popBackStack()
        }

        return view
    }
}