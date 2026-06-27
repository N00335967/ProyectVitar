package com.proyecto.vitar.data.mapper

import com.proyecto.vitar.data.remote.dto.UsuarioDto
import com.proyecto.vitar.domain.model.Usuario

fun UsuarioDto.toDomain(): Usuario {
    return Usuario(
        id = this.id,
        nombre = this.nombre,
        correo = this.correo
        // password no debería ir aquí, por seguridad no se debe pasar a la capa de dominio
    )
}