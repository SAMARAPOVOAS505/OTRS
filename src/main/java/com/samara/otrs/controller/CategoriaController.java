package com.samara.otrs.controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoriaController {

    @GetMapping
    public List<Map<String, Object>> getCategorias() {
        List<Map<String, Object>> categorias = new ArrayList<>();

        Map<String, Object> software = new HashMap<>();
        software.put("categoria", "Software");
        software.put("subcategorias", Arrays.asList(
            "01 - Acessos",
            "Cadastro",
            "Alteração",
            "Inativação",
            "03 - SIC",
            "Erros Gerais (Obrigatório Evidência)"
        ));

        Map<String, Object> hardware = new HashMap<>();
        hardware.put("categoria", "Hardware");
        hardware.put("subcategorias", Arrays.asList(
            "02 - Equipamentos",
            "Headset",
            "Teclado",
            "Mouse",
            "Computador/Notebook",
            "Impressora",
            "Cadeira"
        ));

        categorias.add(software);
        categorias.add(hardware);

        return categorias;
    }
}