import { Component, OnInit } from '@angular/core';
import { User } from '../system-user/model';
//import { Service } from '../system-user/service';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Location } from '@angular/common';

import { HttpClient, HttpParams } from '@angular/common/http';

import { throwError, Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';


@Component({
  selector: 'systemuser-cmp',
  moduleId: module.id,
  templateUrl: 'systemuser.component.html'
})

export class SystemUserComponent implements OnInit {
  closeModal: string;
  users: any = [ ]
  user = new User()
  base_url: string

  constructor(private http: HttpClient, private modalService: NgbModal, private route: ActivatedRoute, private location: Location, private router: Router) { }

  ngOnInit() {
    // this.users = [{'id': 1, 'first_name': 'cccc'}]
    this.base_url = 'http://localhost:9199';
    
    this.http.get<any>(this.base_url + '/users')//tenants
      .subscribe({
        next: data => {
          for(let i=0; i<data.length; i++){
            let item = data[i];
            this.users.push(item);
          }
        },
        error: error => {
          debugger
          let errorMessage = error.message;
          console.error('There was an error!', error);
        }
      })
  }

  save(data) {
    debugger
    this.http.post<any>(this.base_url + '/add-user', data)
      .subscribe({
        next: data => {
          for(let i=0; i<data.length; i++){
            let item = data[i];
            this.users.push(item);
          }
        },
        error: error => {
          debugger
          let errorMessage = error.message;
          console.error('There was an error!', error);
        }
      })
  }

  getTenants() {
    let params = new HttpParams();
    //params = params.set('id', '1');

    this.http.get<any>(this.base_url + '/tenants', { params })
      .subscribe({
        next: data => {
          this.users = data.user;
        },
        error: error => {
          debugger
          let errorMessage = error.message;
          console.error('There was an error!', error);
        }
      })
  }

  delete(usr){
    this.http.post<any>(this.base_url + '/delete', usr.id)
      .subscribe({
        next: data => {
          this.getTenants();
        },
        error: error => {
          debugger
          let errorMessage = error.message;
          console.error('There was an error!', error);
        }
      })
  }

  triggerModal(content) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((res) => {
      this.closeModal = `Closed with: ${res}`;
    }, (res) => {
      this.closeModal = `Dismissed ${this.getDismissReason(res)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }
}
