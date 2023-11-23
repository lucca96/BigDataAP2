import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LivroModel} from '../model/livro.model';

@Injectable({
  providedIn: 'root'
})
export class LivroService {

  constructor(private http: HttpClient) { }

  public getLivro(idAutor:any) : Observable<LivroModel[]> {
    return this.http.get<LivroModel[]>(`https://localhost:8080//autor/${idAutor}/livro`);
  }

  public createLivro(idAutor: any, livro: LivroModel): Observable<LivroModel> {
    return this.http.post<LivroModel>(`https://localhost:8080//autor/${idAutor}/livro`, livro);

  }
}
