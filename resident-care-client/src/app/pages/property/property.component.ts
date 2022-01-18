import { Component, OnInit } from '@angular/core';
import { User } from '../system-user/model';
import { Building} from '../property/model';
import { Apt} from '../property/apt';
//import { Service } from '../system-user/service';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Location } from '@angular/common';

import { HttpClient, HttpParams } from '@angular/common/http';

import { throwError, Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';


@Component({
  selector: 'property-cmp',
  moduleId: module.id,
  templateUrl: 'property.component.html'
})

export class PropertyComponent implements OnInit {
  closeModal: string;
  buildings: any = [ ]
  apts: any = [ ]
  building = new Building()
  apt = new Apt();
  base_url: string

  constructor(private http: HttpClient, private modalService: NgbModal, private route: ActivatedRoute, private location: Location, private router: Router) { }

  ngOnInit() {
    this.base_url = 'http://localhost:9199';
    
    this.getBuilding();
    this.getApt();
  }

  saveBuilding(data) {
    debugger
    this.http.post<any>(this.base_url + '/add-building', data)
      .subscribe({
        next: data => {
          this.getBuilding();
        },
        error: error => {
          debugger
          let errorMessage = error.message;
          console.error('There was an error!', error);
        }
      })
  }

  saveApt(data) {
    debugger
    this.http.post<any>(this.base_url + '/add-apt', data)
      .subscribe({
        next: data => {
          this.getApt();
        },
        error: error => {
          debugger
          let errorMessage = error.message;
          console.error('There was an error!', error);
        }
      })
  }

  getBuilding() {
    let params = new HttpParams();
    this.http.get<any>(this.base_url + '/buildings', { params })
      .subscribe({
        next: data => {
          debugger
          this.buildings = data;
        },
        error: error => {
          debugger
          let errorMessage = error.message;
          console.error('There was an error!', error);
        }
      })
  }

  getApt() {
    let params = new HttpParams();
    this.http.get<any>(this.base_url + '/apts', { params })
      .subscribe({
        next: data => {
          debugger
          this.apts = data;
        },
        error: error => {
          debugger
          let errorMessage = error.message;
          console.error('There was an error!', error);
        }
      })
  }

  deleteBuilding(bld){
    debugger
    this.http.post<any>(this.base_url + '/delete-building', bld.id)
      .subscribe({
        next: data => {
          this.getBuilding();
        },
        error: error => {
          debugger
          let errorMessage = error.message;
          console.error('There was an error!', error);
        }
      })
  }

  deleteApt(apt){
    this.http.post<any>(this.base_url + '/delete-apt', apt.id)
      .subscribe({
        next: data => {
          this.getApt();
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
