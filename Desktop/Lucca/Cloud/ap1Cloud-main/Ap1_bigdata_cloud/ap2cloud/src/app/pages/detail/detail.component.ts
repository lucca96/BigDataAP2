import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Autor} from 'src/app/model/autor.model';
import { LivroService } from 'src/app/services/livro.service';
import { AutorService } from 'src/app/services/autor.service';
import { LivroModel} from 'src/app/model/livro.model';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})

export class DetailComponent implements OnInit {

  autor?: Autor;
  livro?: LivroModel[];
  showCriarLivro = false;

  constructor(private autorService: AutorService,
    private livroService: LivroService,
    private route: ActivatedRoute) {

  }
  ngOnInit(): void {

    let idAutor = this.route.snapshot.params["idAutor"];
    this.autorService.getAutorbyId(idAutor).subscribe(response => {
      this.autor = response;
      console.log(response);
    });

    this.carregaProduto();
  }

  private carregaProduto() {
    let idAutor = this.route.snapshot.params["idAutor"];
    this.livroService.getLivro(idAutor).subscribe(response => {
      this.livro = response;
    });
  }

  public mostrarCriarProduto() {
    this.showCriarLivro = true;
  }

  public atualizarProduto() {
    this.carregaProduto();
  }

}
