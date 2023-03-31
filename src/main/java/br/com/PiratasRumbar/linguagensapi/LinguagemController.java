package br.com.PiratasRumbar.linguagensapi;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LinguagemController {

    @Autowired
    private LinguagemRepository repositorio;

    @GetMapping("/linguagens")
    public List<Linguagem> obterLingens() {
        List<Linguagem> linguagens = repositorio.findByOrderByRanking();
        return linguagens;
    }

    @GetMapping("/linguagens/{id}")
    public Linguagem obterLinguagemPorId(@PathVariable String id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/linguagens")
    public ResponseEntity<Linguagem> cadastrarLinguagens(@RequestBody Linguagem linguagem) {
        return new ResponseEntity<>(repositorio.save(linguagem),HttpStatus.CREATED);

    }

    @PutMapping("/linguagens/{id}")
    public Linguagem atualizar(@PathVariable String id, @RequestBody Linguagem linguagem) {
        if (!repositorio.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        linguagem.setId(id);
        return repositorio.save(linguagem);
    }

    @DeleteMapping("/linguagens/{id}")
    public void ExcluirLinguagens(@PathVariable String id, @RequestBody Linguagem linguagem) {
        repositorio.deleteById(id);
    }
}
