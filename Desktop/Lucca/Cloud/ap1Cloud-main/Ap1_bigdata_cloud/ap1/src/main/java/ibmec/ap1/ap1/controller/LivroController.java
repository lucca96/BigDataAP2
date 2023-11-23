package ibmec.ap1.ap1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibmec.ap1.ap1.exception.AutorException;
import ibmec.ap1.ap1.exception.LivroException;
import ibmec.ap1.ap1.model.Autor;
import ibmec.ap1.ap1.model.Livro;
import ibmec.ap1.ap1.service.LivroService;
import ibmec.ap1.ap1.service.AutorService;

@RestController
@RequestMapping("/autor/{idAutor}/livro")
@CrossOrigin
class resourceNameController {

    @Autowired
    LivroService livroService;

    @Autowired AutorService autorService;

    @GetMapping
    public ResponseEntity<List<Livro>> getAll(@PathVariable("idAutor") long idAutor) {
        try {
            Optional<Autor> opAutor = this.autorService.findById(idAutor);

            if (opAutor.isPresent() == false) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);    
            }

            return new ResponseEntity<>(opAutor.get().getLivro(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Livro> getById(@PathVariable("id") long id) {
        Optional<Livro> existingItemOptional = livroService.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Livro> create(@PathVariable("idAutor") long idAutor, @RequestBody Livro item) throws LivroException {
        Livro savedItem = livroService.save(idAutor, item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Livro> update(@PathVariable("id") long id, @RequestBody Livro item) throws LivroException {
        return new ResponseEntity<>(livroService.update(id, item), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) throws LivroException {
        livroService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
