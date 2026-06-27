package com.proyecto.vitar.data.repository

import com.proyecto.vitar.data.mapper.toDomain
import com.proyecto.vitar.data.remote.datasource.RemoteDataSource
import com.proyecto.vitar.domain.model.Usuario
import com.proyecto.vitar.domain.repository.UsuarioRepository

class UsuarioRepositoryImpl(private val remoteDataSource: RemoteDataSource):UsuarioRepository {
    override suspend fun login(correo: String, password: String): Usuario? {
        // Aquí llamar dataSource / dominio
        val response = remoteDataSource.iniciarSesion(correo, password)
        return response.usuario?.toDomain() // Asegúrate de tener esta función mapper
    }
}