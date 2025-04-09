package com.samara.otrs.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoria;
    private String subcategoria;
    private String descricao;
    private String status;

    // Info do criador
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;
    private String nomeFuncionario; // usado em Software

    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    // SLA em tempo real = agora - dataCriacao (calculado no frontend ou em método get)
    private String ipOrigem;
    private String evidenciasBase64;

    // SOFTWARE
    @ElementCollection
    private List<String> sistemas;
    private String carteira;
    private String cargo;
    @ElementCollection
    private List<String> operacoes;
    private String tipoAlteracao;
    @ElementCollection
    private List<String> erros;

    // HARDWARE
    private String sala;
    private String posicaoPA;

    // Resolução (técnico preenche)
    private String resumoResolucao;
    private boolean houveTroca; // se hardware
    private String tipoEquipamento; // "Novo" ou "Usado"
}
