package com.example.codialapp.models

class Group (
    var id:Int? =null,
    var name:String? = null,
    var mentor:Mentor?=null,
    var time:String? = null,
    var daysOfWeek:String? = null,
    var course: Course? =null,
    var open:Boolean? = null
        ):java.io.Serializable