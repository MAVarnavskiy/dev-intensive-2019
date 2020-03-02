package ru.skillbranch.devintensive.models

class UserView(
    val id: String,
    val fullname: String,
    val nickName:String,
    val avatar: String? = null,
    val status: String? = "offline",
    val initials: String?

) {
    fun printMe() {
        println("""
            $id
            $fullname
            $nickName
            $avatar
            $status
            $initials      
        """.trimIndent())

    }
}