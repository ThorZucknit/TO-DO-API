package com.treino.api_treino.service;

import com.treino.api_treino.entity.TarefasModel;
import com.treino.api_treino.repository.TarefaRepository;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TarefaService {

    private TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public List<TarefasModel> findAllTaks(){
        return tarefaRepository.findAll();
    }

    public TarefasModel saveTaks(TarefasModel tarefa){
        return tarefaRepository.save(tarefa);
    }

    public TarefasModel findByTaks(Long id){
        TarefasModel tarefa = tarefaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Tarefa não encontrada"));


        return tarefa;

    }

    public TarefasModel atualizarTaks(Long id, TarefasModel dadosAtualizados){
        TarefasModel tarefasModel = tarefaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Tarefa não encontrada"));

        tarefasModel.setTitulo(dadosAtualizados.getTitulo());
        tarefasModel.setDescricao(dadosAtualizados.getDescricao());
        tarefasModel.setConcluida(dadosAtualizados.isConcluida());

        return tarefaRepository.save(tarefasModel);
    }

    public void deleteTaks(Long id){

        TarefasModel tarefasModel = tarefaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Tarefa não encontrada"));
         tarefaRepository.delete(tarefasModel);
    }

    public List<TarefasModel> findForStatus(boolean concluida){

        return tarefaRepository.findByConcluida(concluida);

    }

    public List<TarefasModel> findByTitle(String title){

        return tarefaRepository.findByTituloContainingIgnoreCase(title);
    }

    public List<TarefasModel> listOrder(String campo,String descricao){

        Sort order;

        if(descricao.equalsIgnoreCase("desc")){
            order = Sort.by(campo).descending();
        }else{
            order = Sort.by(campo).ascending();
        }

        return tarefaRepository.findAll(order);
    }


}
