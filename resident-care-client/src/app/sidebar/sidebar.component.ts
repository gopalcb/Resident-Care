import { Component, OnInit } from '@angular/core';


export interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}

export const ROUTES: RouteInfo[] = [
    { path: '/properties',     title: 'Properties',         icon:'nc-bank',       class: '' },
    { path: '/system-users',     title: 'System Users',         icon:'nc-circle-10',       class: '' },
    { path: '/employees',     title: 'Employees',         icon:'nc-badge',       class: '' },
    { path: '/email-service',     title: 'Email Service',         icon:'nc-email-85',       class: '' },
    { path: '/inspections',     title: 'Inspection Service',         icon:'nc-check-2',       class: '' },
    { path: '/notices',     title: 'Notice Service',         icon:'nc-bell-55',       class: '' }
];

@Component({
    moduleId: module.id,
    selector: 'sidebar-cmp',
    templateUrl: 'sidebar.component.html',
})

export class SidebarComponent implements OnInit {
    public menuItems: any[];
    ngOnInit() {
        this.menuItems = ROUTES.filter(menuItem => menuItem);
    }
}
