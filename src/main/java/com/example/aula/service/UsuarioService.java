package com.example.aula.service;

import com.example.aula.dto.UsuarioDTO;
import com.example.aula.dto.UsuarioResponseDTO;
import com.example.aula.exception.EmailJaCadastradoException;
import com.example.aula.model.Usuario;
import com.example.aula.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UsuarioResponseDTO toDto(Usuario usuario) {
        return new UsuarioResponseDTO(usuario.getNome(), usuario.getEmail());
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Usuario salvar(@Valid UsuarioDTO usuarioDTO) {
        usuarioRepository.findByEmail(usuarioDTO.getEmail())
                .ifPresent(u -> {
                    throw new EmailJaCadastradoException("Email já cadastrado: ");});

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(bCryptPasswordEncoder.encode(usuarioDTO.getSenha()));

        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(String email, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(bCryptPasswordEncoder.encode(dto.getSenha()));

        return usuarioRepository.save(usuario);
    }

    public void excluir(String email) {
        Usuario usuarioExcluir = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        usuarioRepository.delete(usuarioExcluir);
    }

}
