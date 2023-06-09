package com.example.codialapp.db

import com.example.codialapp.models.Course
import com.example.codialapp.models.Group
import com.example.codialapp.models.Mentor
import com.example.codialapp.models.Student

interface MyDbInterface {
    fun addCourse(course: Course)
    fun addMentor(mentor: Mentor)
    fun addGroup(group: Group)
    fun addStudent(student: Student)

    fun getAllCourses():ArrayList<Course>
    fun getAllMentors():ArrayList<Mentor>
    fun getAllGroups():ArrayList<Group>
    fun getAllStudents():ArrayList<Student>

    fun getCourseById(id:Int):Course
    fun getMentorById(id:Int):Mentor
    fun getGroupById(id: Int):Group

    fun deleteCourse(course: Course)
    fun deleteMentor(mentor: Mentor)
    fun deleteGroup(group: Group)
    fun deleteStudent(student: Student)

    fun editMentors(mentor: Mentor)
    fun editGroup(group: Group)
    fun editStudent(student: Student)

    fun editGroupOpen(group: Group)
}