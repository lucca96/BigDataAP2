import { AfterViewInit, Component, OnDestroy, OnInit } from '@angular/core';
import { AutorService } from '../services/autor.service';
import { Autor } from '../model/autor.model';
import { Router } from '@angular/router';
@Component({
  selector: 'app-post',
  templateUrl: './autor.component.html',
  styleUrls: ['./autor.component.css']
})
export class AutorComponent  implements OnInit{
  posts: Autor[]= [];

  constructor(private autorService: AutorService, private router: Router) {

  }

  ngOnInit(): void {
    this.autorService.getAutor().subscribe(response => {
      this.posts = response;
    });
  }

  redirectToDetail(id: any) {
      this.router.navigate(["detail", id]);
  }


 /* ngBeforeViewInit(): void {

  }

  ngAfterViewInit(): void {

  }

  ngChanges(): void {

  }

  ngOnDestroy(): void {

  }
*/
}

