
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { LivroService } from '../services/livro.service';
import { LivroModel } from '../model/livro.model';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-create-livro',
  templateUrl: './create-livro.component.html',
  styleUrls: ['./create-livro.component.css']
})
export class CreateLivroComponent {

  livro = new FormControl('', [Validators.required]);
  @Output() newLivroEvent = new EventEmitter();
  @Input() idMarca:any = '';

  constructor(private livroService: LivroService, private snackBar: MatSnackBar) {

  }

  public criarNovoLivro() {
    if (this.livro.hasError("required")) {
      return;
    }

    let livro: LivroModel = {
      nomeLivro: this.livro.value as string,
      categoria: this.livro.value as string,
      descricao: this.livro.value as string,
      preco: this.livro.value as string,
    };

    this.livroService.createLivro(this.idMarca, livro).subscribe(response => {
      this.snackBar.open("Livro criado com sucesso", "Ok");
      this.newLivroEvent.emit();
    });
  }

}


