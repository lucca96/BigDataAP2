package ap1.cloud.ap1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ap1.cloud.ap1.model.Livro;
import ap1.cloud.ap1.model.Autor;
import ap1.cloud.ap1.repository.LivroRepository;

@Service
public class LivroService {
    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorService autorService;

    public List<Livro> findAll() {
        return this.livroRepository.findAll();
    }

    public Optional<Livro> findById(long id) {
        return this.livroRepository.findById(id);
    }

    public Livro create(long idAutor, Livro newLivro) throws Exception {
        Optional<Autor> opAutor = this.autorService.findById(idAutor);

        if (opAutor.isPresent() == false) {
            throw new Exception("Não encontrei o autor para adicionar o livro");
        }

        Autor autor = opAutor.get();
        autor.addLivro(newLivro);
        this.autorService.saveLivro(autor);

        Livro result = autor.getLivros().get(autor.getLivros().size() - 1);
        return result;
    }

    public Livro update(long id, Livro newData) throws Exception {
        Optional<Livro> existingItemOptional = livroRepository.findById(id);

        if (existingItemOptional.isPresent() == false)
            throw new Exception("Não encontrei o livro a ser atualizado");

        Livro existingItem = existingItemOptional.get();

        existingItem.setTitulo(newData.getTitulo());
        existingItem.setId(newData.getId());
        existingItem.setAno(newData.getAno());

        livroRepository.save(existingItem);
        return existingItem;
    }

    public void delete(long id) throws Exception {
        Optional<Livro> livro = this.livroRepository.findById(id);

        if (livro.isPresent() == false)
            throw new Exception("Não encontrei o livro a ser atualizado");

        this.livroRepository.delete(livro.get());
    }

}
