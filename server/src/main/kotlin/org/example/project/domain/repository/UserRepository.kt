package org.example.project.domain.repository

import org.example.project.domain.entities.User

interface UserRepository {
    fun create(user: User)
    fun login(email:String): User
    fun logout()
    fun delete()
}
