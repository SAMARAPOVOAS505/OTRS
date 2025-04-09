package com.samara.otrs.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ChamadoResponseDTO {
    private Long id;
    private String nomeUsuario;
    private LocalDateTime dataCriacao;
    private String categoria;
    private String subcategoria;
    private String descricao;
    private String ip;
    private String status;
    private long sla;

    // Dados específicos
    private List<String> sistemas;
    private String carteira;
    private String cargo;
    private String nomeFuncionario;
    private String caminhoDoPrint;
}

