import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'cooperative',
        data: { pageTitle: 'coopcycleApp.cooperative.home.title' },
        loadChildren: () => import('./cooperative/cooperative.module').then(m => m.CooperativeModule),
      },
      {
        path: 'customer',
        data: { pageTitle: 'coopcycleApp.customer.home.title' },
        loadChildren: () => import('./customer/customer.module').then(m => m.CustomerModule),
      },
      {
        path: 'delivery-man',
        data: { pageTitle: 'coopcycleApp.deliveryMan.home.title' },
        loadChildren: () => import('./delivery-man/delivery-man.module').then(m => m.DeliveryManModule),
      },
      {
        path: 'course',
        data: { pageTitle: 'coopcycleApp.course.home.title' },
        loadChildren: () => import('./course/course.module').then(m => m.CourseModule),
      },
      {
        path: 'menu',
        data: { pageTitle: 'coopcycleApp.menu.home.title' },
        loadChildren: () => import('./menu/menu.module').then(m => m.MenuModule),
      },
      {
        path: 'order',
        data: { pageTitle: 'coopcycleApp.order.home.title' },
        loadChildren: () => import('./order/order.module').then(m => m.OrderModule),
      },
      {
        path: 'product',
        data: { pageTitle: 'coopcycleApp.product.home.title' },
        loadChildren: () => import('./product/product.module').then(m => m.ProductModule),
      },
      {
        path: 'order-content',
        data: { pageTitle: 'coopcycleApp.orderContent.home.title' },
        loadChildren: () => import('./order-content/order-content.module').then(m => m.OrderContentModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
