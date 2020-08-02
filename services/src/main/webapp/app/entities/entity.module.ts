import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'region',
        loadChildren: () => import('./region/region.module').then(m => m.Ec2VivoRegionModule),
      },
      {
        path: 'country',
        loadChildren: () => import('./country/country.module').then(m => m.Ec2VivoCountryModule),
      },
      {
        path: 'location',
        loadChildren: () => import('./location/location.module').then(m => m.Ec2VivoLocationModule),
      },
      {
        path: 'user-billing',
        loadChildren: () => import('./user-billing/user-billing.module').then(m => m.Ec2VivoUserBillingModule),
      },
      {
        path: 'user-ec-2-vivo',
        loadChildren: () => import('./user-ec-2-vivo/user-ec-2-vivo.module').then(m => m.Ec2VivoUserEc2VivoModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class Ec2VivoEntityModule {}
