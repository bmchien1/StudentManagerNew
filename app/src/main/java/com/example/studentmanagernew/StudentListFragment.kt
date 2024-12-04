package com.example.studentmanagernew

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class StudentListFragment : Fragment() {

    private lateinit var studentList: MutableList<Student>
    private lateinit var adapter: ArrayAdapter<Student>
    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_student_list, container, false)
        setHasOptionsMenu(true)

        listView = view.findViewById(R.id.listViewStudents)
        studentList = mutableListOf()
        adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            studentList
        )
        listView.adapter = adapter

        registerForContextMenu(listView)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuAdd) {
            findNavController().navigate(R.id.action_studentListFragment_to_studentDetailFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        when (item.itemId) {
            R.id.contextEdit -> {
                val student = studentList[info.position]
                val action = StudentListFragmentDirections
                    .actionStudentListFragmentToStudentDetailFragment(student)
                findNavController().navigate(action)
            }
            R.id.contextRemove -> {
                studentList.removeAt(info.position)
                adapter.notifyDataSetChanged()
                Toast.makeText(context, "Student removed", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onContextItemSelected(item)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerForContextMenu(listView)
    }
}