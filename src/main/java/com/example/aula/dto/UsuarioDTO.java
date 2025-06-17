package com.example.aula.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {
    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;
    @NotBlank(message = "O email não pode estar em branco")
    @Email(message = "O email deve ser válido")
    private String email;
    @NotBlank(message = "A senha não pode estar em branco")
    @Size(min = 3, message = "A senha deve ter no mínimo 3 caracteres")
    private String senha;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public @NotBlank(message = "O nome não pode estar em branco") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome não pode estar em branco") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "O email não pode estar em branco") @Email(message = "O email deve ser válido") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "O email não pode estar em branco") @Email(message = "O email deve ser válido") String email) {
        this.email = email;
    }

    public @NotBlank(message = "A senha não pode estar em branco") String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank(message = "A senha não pode estar em branco") String senha) {
        this.senha = senha;
    }
}
