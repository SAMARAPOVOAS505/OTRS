package com.samara.otrs.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChamadoRequestDTO {
    private String categoria; // Hardware ou Software
    private String subcategoria;

    // Dados comuns
    private String descricao;
    private Long usuarioId;

    // Campos específicos de Hardware
    private String sala;
    private String posicaoPA;

    // Campos específicos de Software - Cadastro / Alteração / Erro / Inativação
    private String nomeFuncionario;
    private List<String> sistemas; // SIC, AD, Spark, etc
    private String carteira;
    private String cargo;
    private List<String> operacoes; // Cadastrar, Alterar, Inativar
    private String tipoAlteracao; // Senha, Carteira, Cargo
    private List<String> erros; // SIC, Vonix, Spark, etc
    private String evidenciasBase64; // Para o print do erro (imagem em base64)
    private String ipOrigem;
    private String resumoResolucao;
    private Boolean houveTroca;
    private String tipoEquipamento;
}
