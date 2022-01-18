import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { AdminLayoutRoutes } from './admin-layout.routing';

import { SystemUserComponent }      from '../../pages/system-user/systemuser.component';
import { PropertyComponent } from '../../pages/property/property.component';
import { EmployeeComponent } from '../../pages/employee/employee.component';
import { IconsComponent }           from '../../pages/icons/icons.component';
import { EmailComponent }           from '../../pages/email-service/email.component';
import { InspectionComponent }           from '../../pages/inspection/inspection.component';
import { NoticeComponent }           from '../../pages/notice/notice.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
//import { Service } from '../../pages/system-user/service';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    NgbModule,
    HttpClientModule
  ],
  declarations: [
    SystemUserComponent,
    PropertyComponent,
    IconsComponent,
    EmployeeComponent,
    EmailComponent,
    InspectionComponent,
    NoticeComponent
  ]
})

export class AdminLayoutModule {}
