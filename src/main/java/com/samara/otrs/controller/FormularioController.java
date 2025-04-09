package com.samara.otrs.controller;

import com.samara.otrs.dto.ChamadoFormularioDTO;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/chamados")
@CrossOrigin(origins = "http://localhost:3000")
public class FormularioController {

    @GetMapping("/formulario")
    public ChamadoFormularioDTO getFormularioOptions() {
        ChamadoFormularioDTO dto = new ChamadoFormularioDTO();

        dto.setCategorias(List.of("Hardware", "Software"));

        Map<String, List<String>> subcategorias = new HashMap<>();
        subcategorias.put("Hardware", List.of("Headset", "Teclado", "Mouse", "Computador/Notebook", "Impressora", "Cadeira"));
        subcategorias.put("Software", List.of("Cadastro", "Alteração", "Erro", "Inativação"));
        dto.setSubcategorias(subcategorias);

        dto.setOpcoesCadastro(List.of("SIC", "Spark", "Vonix", "Pasta", "AD"));
        dto.setOpcoesAlteracao(List.of("Senha", "Carteira", "Cargo"));
        dto.setOpcoesErro(List.of("SIC", "Vonix", "Spark", "Windows", "Sites"));
        dto.setSistemas(List.of("SIC", "AD", "Windows", "Vonix", "Spark"));
        dto.setCarteiras(List.of("Recovery", "MP Itapeva", "MP", "Pan", "Pan Veículos", "Divzero", "Return", "Arc4"));
        dto.setCargos(List.of("Operador", "Monitor", "Assistente de Monitoria", "Assistente de Supervisão", "Coordenador", "Gerente", "Control", "MIS"));

        return dto;
    }
}