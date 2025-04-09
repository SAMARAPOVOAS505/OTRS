package com.samara.otrs.dto;


import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ChamadoFormularioDTO {
    private List<String> categorias;
    private Map<String, List<String>> subcategorias;
    private List<String> opcoesCadastro;
    private List<String> opcoesAlteracao;
    private List<String> opcoesErro;
    private List<String> sistemas;
    private List<String> carteiras;
    private List<String> cargos;
}

