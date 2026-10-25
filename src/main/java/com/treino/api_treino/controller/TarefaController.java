package com.treino.api_treino.controller;

import com.treino.api_treino.entity.TarefasModel;
import com.treino.api_treino.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/list")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService){
        this.tarefaService = tarefaService;
    }

    @GetMapping("/all")
    @Operation(summary = "Lista todas tarefas")
    public List<TarefasModel> listAlltaks(){
       return tarefaService.findAllTaks();
    }

    @PostMapping("/cadastrar")
    @Operation(summary = "Cria uma nova tarefa",description ="Rota cria uma nova tarefa e insere no banco de dados" )
    @ApiResponses(value={
            @ApiResponse(responseCode = "201",description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "400",description = "Error na criação da tarefa")
    })
    public ResponseEntity<TarefasModel> createTaks(@Valid @RequestBody TarefasModel tarefa){

        TarefasModel newTaks = tarefaService.saveTaks(tarefa);

        return ResponseEntity.status(HttpStatus.CREATED).body(newTaks);

    }

    @GetMapping("/find/{id}")
    @Operation(summary = "Lista a tarefa pelo id", description = "Rota lista a tarefa por id")
    @Parameter(description = "Usuário manda o id no caminho da requisição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Tarefa encontrada"),
            @ApiResponse(responseCode = "404",description = "Tarefa não encontrada")
    })
    public TarefasModel findByTaks(@PathVariable Long id){

        return  tarefaService.findByTaks(id);
    }

    @GetMapping("/status")
    @Operation(summary = "Lista status de tarefas",description = "lista todas as tarefas atráves do status ")
    @Parameter(description = "Usuário passa por parametro o concluído e mostra true ou false")
    public ResponseEntity<List<TarefasModel>> findForStatus(@RequestParam boolean concluida){

        List<TarefasModel> tasks = tarefaService.findForStatus(concluida);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/findtitle")
    @Operation(summary = "Procura pelo titulo da tarefa",description = "Procura como se fosse um where no banco de dados o titulo digitado ")
    @Parameter(description = "Através dos paremetro digitado ele procura no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Tarefa encontrada"),
            @ApiResponse(responseCode = "404",description = "Tarefa encontrada")
    })
    public ResponseEntity<List<TarefasModel>> findTitle(@RequestParam String title){

        List<TarefasModel> tasks = tarefaService.findByTitle(title);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/order")
    public ResponseEntity<List<TarefasModel>> listOrder(@RequestParam(defaultValue = "id") String campo, @RequestParam(defaultValue = "asc") String direcao){

        return ResponseEntity.ok(tarefaService.listOrder(campo, direcao));
    }


    @PutMapping("/{id}")
    @Operation(summary = "Tarefa concluída",description = "Altera a tarefa para concluída")
    @Parameter(description = "Passa o id para procurar a tarefa e concluir ela")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Tarefa concluída com sucesso"),
            @ApiResponse(responseCode = "404",description = "Tarefa não encontrada para ser concluída")

    })
    public ResponseEntity<TarefasModel> taskconcluida(@PathVariable Long id, @Valid @RequestBody TarefasModel tarefasModel){

        TarefasModel updateTaks = tarefaService.atualizarTaks(id,tarefasModel);
        return ResponseEntity.ok(updateTaks);

    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deleta tarefa",description = "Deleta a tarefa através do id")
    @Parameter(description = "Atráves do id passado ela deleta a tarefa do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Tarefa deletada"),
            @ApiResponse(responseCode = "404",description = "Tarefa não encontrada no banco de dados")
    })
    public ResponseEntity<Void> deleteTaks(@PathVariable Long id){

        tarefaService.deleteTaks(id);
        return ResponseEntity.noContent().build();

    }



}
