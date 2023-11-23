import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Autor } from '../model/autor.model';

@Injectable({
  providedIn: 'root'
})
export class AutorService {

  constructor(private httpClient: HttpClient) { }

public getAutor() : Observable<Autor[]> {
  return this.httpClient.get<Autor[]>("https://localhost:8080/autor")
}


public getAutorbyId(id : number) : Observable<Autor> {
  return this.httpClient.get<Autor>("https://localhost:8080/autor/" + id)
}
}
