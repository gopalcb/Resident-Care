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
  selector: 'email-cmp',
  moduleId: module.id,
  templateUrl: 'email.component.html'
})

export class EmailComponent implements OnInit {
  closeModal: string;
  users: any = [ ];
  user = new User();
  base_url: string;
  emails: string;
  email: any;
  allData: any;

  constructor(private http: HttpClient, private modalService: NgbModal, private route: ActivatedRoute, private location: Location, private router: Router) { }

  ngOnInit() {
    this.base_url = 'http://localhost:9192';
    this.loadEmail();
  }

  loadEmail(){
    this.http.get<any>(this.base_url + '/get-emails')
      .subscribe({
        next: data => {
          debugger
          this.emails = data.map(x => x.email_subject);
          this.allData = data;
        },
        error: error => {
          debugger
          let errorMessage = error.message;
          console.error('There was an error!', error);
        }
      })
  }

  showEmail(s){
    this.email = this.allData.filter(x => x.email_subject == s);
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
