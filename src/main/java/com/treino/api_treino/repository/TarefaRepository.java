package com.treino.api_treino.repository;

import com.treino.api_treino.entity.TarefasModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<TarefasModel,Long> {

    List<TarefasModel> findByConcluida(boolean concluida);
    List<TarefasModel> findByTituloContainingIgnoreCase(String titulo);}
