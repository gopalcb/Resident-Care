import { Routes } from '@angular/router';

import { SystemUserComponent } from '../../pages/system-user/systemuser.component';
import { PropertyComponent } from '../../pages/property/property.component';
import { IconsComponent } from '../../pages/icons/icons.component';
import { EmployeeComponent } from '../../pages/employee/employee.component';
import { EmailComponent }           from '../../pages/email-service/email.component';
import { InspectionComponent }           from '../../pages/inspection/inspection.component';
import { NoticeComponent }           from '../../pages/notice/notice.component';

export const AdminLayoutRoutes: Routes = [
    { path: 'properties',   component: PropertyComponent },
    { path: 'system-users',   component: SystemUserComponent },
    { path: 'employees',   component: EmployeeComponent },
    { path: 'email-service',   component: EmailComponent },
    { path: 'inspections',   component: InspectionComponent },
    { path: 'notices',   component: NoticeComponent },
    { path: 'icons',          component: IconsComponent }
];
