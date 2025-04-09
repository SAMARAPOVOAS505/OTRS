package com.samara.otrs.controller;

import com.samara.otrs.dto.ChamadoRequestDTO;
import com.samara.otrs.dto.ChamadoResponseDTO;
import com.samara.otrs.model.Chamado;
import com.samara.otrs.repository.ChamadoRepository;
import com.samara.otrs.repository.UserRepository;
import com.samara.otrs.model.User;


import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chamados")
@CrossOrigin(origins = "http://localhost:3000")
public class ChamadoController {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Chamado criarChamado(@RequestBody Chamado chamado) {
        chamado.setDataCriacao(LocalDateTime.now());
        chamado.setStatus("ABERTO");
        return chamadoRepository.save(chamado);
    }

    @GetMapping
    public List<Chamado> listarTodos() {
        return chamadoRepository.findAll();
    }

    @GetMapping("/usuario/{userId}")
public ResponseEntity<List<Chamado>> listarPorUsuario(@PathVariable Long userId) {
    return userRepository.findById(userId)
        .map(user -> ResponseEntity.ok(chamadoRepository.findByUsuario(user)))
        .orElse(ResponseEntity.notFound().build());
}


    @PutMapping("/{id}")
    public Chamado atualizarChamado(@PathVariable Long id, @RequestBody Chamado atualizado) {
        Chamado chamado = chamadoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Chamado não encontrado"));
    
        if (atualizado.getStatus() != null) {
            chamado.setStatus(atualizado.getStatus());
        }
    
        if (atualizado.getDescricao() != null) {
            chamado.setDescricao(atualizado.getDescricao());
        }
    
        chamado.setDataAtualizacao(LocalDateTime.now());
    
        return chamadoRepository.save(chamado);
    }

    @DeleteMapping("/{id}")
public void deletarChamado(@PathVariable Long id) {
    chamadoRepository.deleteById(id);
}

@PostMapping("/registrar")
public ResponseEntity<String> registrarChamado(@RequestBody ChamadoRequestDTO dto, HttpServletRequest request) {
    Chamado chamado = new Chamado();

    chamado.setCategoria(dto.getCategoria());
    chamado.setSubcategoria(dto.getSubcategoria());
    chamado.setDescricao(dto.getDescricao());
    chamado.setDataCriacao(LocalDateTime.now());
    chamado.setStatus("ABERTO");

    // Usuário que abriu o chamado
    User user = userRepository.findById(dto.getUsuarioId())
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    chamado.setUsuario(user);
    chamado.setNomeFuncionario(dto.getNomeFuncionario());

    chamado.setSistemas(dto.getSistemas());
    chamado.setCarteira(dto.getCarteira());
    chamado.setCargo(dto.getCargo());
    chamado.setOperacoes(dto.getOperacoes());
    chamado.setTipoAlteracao(dto.getTipoAlteracao());
    chamado.setErros(dto.getErros());

    chamado.setSala(dto.getSala());
    chamado.setPosicaoPA(dto.getPosicaoPA());

    chamado.setEvidenciasBase64(dto.getEvidenciasBase64());
    chamado.setIpOrigem(request.getRemoteAddr());

    chamadoRepository.save(chamado);

    return ResponseEntity.ok("Chamado registrado com sucesso!");
}

@GetMapping("/tecnico")
public List<Chamado> listarChamadosParaTecnico() {
    return chamadoRepository.findAll();
}

@PutMapping("/resolver/{id}")
public ResponseEntity<Chamado> resolverChamado(@PathVariable Long id, @RequestBody ChamadoRequestDTO dto) {
    Chamado chamado = chamadoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Chamado não encontrado"));

    chamado.setStatus("RESOLVIDO");
    chamado.setResumoResolucao(dto.getResumoResolucao());
    chamado.setHouveTroca(dto.getHouveTroca());
    chamado.setTipoEquipamento(dto.getTipoEquipamento());
    chamado.setDataAtualizacao(LocalDateTime.now());

    chamadoRepository.save(chamado);

    return ResponseEntity.ok(chamado);
}

@GetMapping("/todos")
public ResponseEntity<List<ChamadoResponseDTO>> listarChamados() {
    List<Chamado> chamados = chamadoRepository.findAll();

    List<ChamadoResponseDTO> response = chamados.stream().map(chamado -> {
        ChamadoResponseDTO dto = new ChamadoResponseDTO();

        dto.setId(chamado.getId());
        dto.setNomeUsuario(chamado.getUsuario().getNome()); // quem abriu
        dto.setDataCriacao(chamado.getDataCriacao());
        dto.setCategoria(chamado.getCategoria());
        dto.setSubcategoria(chamado.getSubcategoria());
        dto.setDescricao(chamado.getDescricao());
        dto.setIp(chamado.getIp()); // se você estiver salvando
        dto.setStatus(chamado.getStatus());

        // calcula SLA em tempo real
        Duration duracao = Duration.between(chamado.getDataCriacao(), LocalDateTime.now());
        dto.setSla(duracao.toMinutes());

        dto.setSistemas(chamado.getSistemas());
        dto.setCargo(chamado.getCargo());
        dto.setCarteira(chamado.getCarteira());
        dto.setNomeFuncionario(chamado.getNomeFuncionario());
        dto.setCaminhoDoPrint(chamado.getCaminhoDoPrint());

        return dto;
    }).collect(Collectors.toList());

    return ResponseEntity.ok(response);
}


}
