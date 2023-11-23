package ibmec.ap1.ap1.controller;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ibmec.ap1.ap1.exception.AutorException;
import ibmec.ap1.ap1.model.Autor;
import ibmec.ap1.ap1.service.AutorService;


@RestController
@RequestMapping("/autor")
@CrossOrigin
class AutorController {

    @Autowired
    AutorService autorService;

    @GetMapping
    public ResponseEntity<List<Autor>> getAll() {
        try {
            List<Autor> items = new ArrayList<Autor>();

            autorService.getAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Autor> getById(@PathVariable("id") long id) {
        Optional<Autor> existingItemOptional = autorService.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Autor> create(@RequestBody Autor item) throws AutorException {
        Autor savedItem = autorService.create(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Autor> update(@PathVariable("id") long id, @RequestBody Autor item) throws AutorException {
        return new ResponseEntity<>(autorService.update(id, item), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) throws AutorException {
        autorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("{id}")
    public ResponseEntity<String> uploadAutorImage(@PathVariable("id") long id, @RequestParam("file") MultipartFile file) throws AutorException, Exception {
        autorService.uploadFileToAutor(file, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}