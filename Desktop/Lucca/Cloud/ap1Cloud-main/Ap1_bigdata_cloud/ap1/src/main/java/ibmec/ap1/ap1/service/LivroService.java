package ibmec.ap1.ap1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azure.core.annotation.Post;

import ibmec.ap1.ap1.model.Livro;
import ibmec.ap1.ap1.repository.LivroRepository;
import ibmec.ap1.ap1.exception.AutorException;
import ibmec.ap1.ap1.exception.LivroException;
import ibmec.ap1.ap1.model.Autor;

@Service
public class LivroService {

        @Autowired
        LivroRepository repository;
    
        @Autowired 
        AutorService autorService;
    
        public List<Livro> findAll() {
            return this.repository.findAll();
        }
    
        public Optional<Livro> findById(long id) {
            return this.repository.findById(id);
        }
    
        public Livro update(long id, Livro newData) throws LivroException {
            Optional<Livro> opLivro = this.repository.findById(id);
    
            if (opLivro.isPresent() == false) {
                throw new LivroException("Não encontrei o comentário a ser atualizado");
            }
    
            Livro livros = opLivro.get();
    
            livros.setTitulo(newData.getTitulo());
            livros.setId(newData.getId());
            livros.setAno(newData.getAno());
    
            this.repository.save(livros);
    
            return livros;
        }
        /* 
        public Livro save(long idAutor, Livro item) throws LivroException, AutorException {
            Optional<Autor> opAutor = this.autorService.findById(idAutor);
    
            if (opAutor.isPresent() == false) {
                throw new LivroException("Livro não encontrado");
            }
    
            Autor autor = opAutor.get();

            //Adiciona um novo livro na autor
            autor.getLivro().add(item);
            
            //Atualiza a autor com o novo livro
            this.autorService.salvarNovoLivro(autor);
           
            return item;
        }*/

        public Livro save(long idAutor, Livro item) throws LivroException {
        Optional<Autor> opAutor = this.autorService.findById(idAutor);

        if (opAutor.isPresent() == false) {
            throw new LivroException("Autor não encontrado");
        }

        Autor autor = opAutor.get();
        item.setAutor(autor);
        this.repository.save(item);
       
        return item;
    }
    
        public void delete(long id) throws LivroException {
            Optional<Livro> opAutor = this.repository.findById(id);
    
            if (opAutor.isPresent() == false) {
                throw new LivroException("Não encontrei o comentário a ser excluído");
            }
    
            this.repository.delete(opAutor.get());
        }
        
    }

