package ibmec.ap1.ap1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ibmec.ap1.ap1.exception.AutorException;
import ibmec.ap1.ap1.model.Autor;
import ibmec.ap1.ap1.repository.AutorRepository;

@Service
public class AutorService {
 @Autowired
    AutorRepository autorRepository;

    @Autowired
    private AzureStorageAccountService azureStorageAccountService;

    public List<Autor> findAll() {
        return this.autorRepository.findAll();
    }

    public Optional<Autor> findById(long id) {
        return this.autorRepository.findById(id);
    }

    public List<Autor> getAll() {
        return this.autorRepository.findAll();
    }

    public void saveOrUpdate(Autor item) {
        this.autorRepository.save(item);
    }

    public Autor create(Autor autor) {

        if (autor.geturlFoto() == null) {
            autor.seturlFoto("https://dummyimage.com/300");
        }

        return this.autorRepository.save(autor);
    }

    public Autor salvarNovoLivro(Autor newData) 
    {
        return this.autorRepository.save(newData);
    }

    public Autor update(long id, Autor newData) throws AutorException{
        Optional<Autor> existingItemOptional = autorRepository.findById(id);

        if (existingItemOptional.isPresent() == false)
            throw new AutorException("Não encontrei o autor a ser atualizado");

        Autor existingItem = existingItemOptional.get();

        existingItem.setNome(newData.getNome());
        existingItem.setId(newData.getId());
        existingItem.setNacionalidade(newData.getNacionalidade());
            autorRepository.save(existingItem);
        return existingItem;
    }

    public void delete(long id) throws AutorException {
        Optional<Autor> endereco = this.autorRepository.findById(id);

        if (endereco.isPresent() == false)
            throw new AutorException("Não encontrei o endereco a ser atualizado");

        this.autorRepository.delete(endereco.get());
    }

    public void saveComentario(Autor autor) {
        this.autorRepository.save(autor);
    }

    public void uploadFileToAutor(MultipartFile file, long id) throws AutorException, Exception {
        
        Optional<Autor> opAutor = this.autorRepository.findById(id);
        
        if (opAutor.isPresent() == false) {
            throw new AutorException("Não encontrei o autor a ser atualizado");
        }

        Autor autor = opAutor.get();
        String ulrImage = this.azureStorageAccountService.uploadFileToAzure(file);
        autor.seturlFoto(ulrImage);
        this.autorRepository.save(autor);
    }

}
