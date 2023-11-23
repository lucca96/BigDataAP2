package ap1.cloud.ap1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ap1.cloud.ap1.model.Autor;
import ap1.cloud.ap1.repository.AutorRepository;

@Service
public class AutorService {

    @Autowired
    private AutorRepository _autorRepository;

    public List<Autor> findAll() {
        return this._autorRepository.findAll();
    }

    public Optional<Autor> findById(long id) {
        return this._autorRepository.findById(id);
    }

    public Autor save(Autor autor) throws Exception {
        if (this._autorRepository.countByNome(autor.getNome()) > 0) {
            throw new Exception("Este nome já existe na base de dados");
        }
        this._autorRepository.save(autor);
        return autor;
    }

    public Autor update(long id, Autor newData) throws Exception {
        Optional<Autor> result = this._autorRepository.findById(id);

        if (result.isPresent() == false) {
            throw new Exception("Não encontrei o autor a ser atualizado");
        }

        Autor autorASerAtualizada = result.get();
        autorASerAtualizada.setNome(newData.getNome());
        autorASerAtualizada.setId(newData.getId());
        autorASerAtualizada.setNacionalidade(newData.getNacionalidade());
        this._autorRepository.save(autorASerAtualizada);
        return autorASerAtualizada;
    }

    public void delete(long id) throws Exception {
        Optional<Autor> autorASerExcluida = this._autorRepository.findById(id);
        if (autorASerExcluida.isPresent() == false) {
            throw new Exception("Não encontrei o autor a ser atualizado");
        }
        this._autorRepository.delete(autorASerExcluida.get());
    }

    public void saveLivro(Autor autor) {
        this._autorRepository.save(autor);
    }

}
