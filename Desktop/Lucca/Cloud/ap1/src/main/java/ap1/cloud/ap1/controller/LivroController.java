package ap1.cloud.ap1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ap1.cloud.ap1.model.Livro;
import ap1.cloud.ap1.service.LivroService;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/livro")
@Tag(name = "Livro", description = "")
class LivroController {

    @Autowired
    LivroService livroService;

    @GetMapping
    public ResponseEntity<List<Livro>> getAll() {
        try {
            return new ResponseEntity<>(livroService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Livro> getById(@PathVariable("id") Long id) {
        Optional<Livro> existingItemOptional = livroService.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{idAutor}")
    public ResponseEntity<Livro> create(@PathVariable("idAutor") long idAutor, @RequestBody Livro livro) {
        try {
            Livro result = this.livroService.create(idAutor, livro);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
            
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Livro> update(@PathVariable("id") Long id, @RequestBody Livro livro) {
        try {
            Livro result = this.livroService.update(id, livro);    
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            this.livroService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
