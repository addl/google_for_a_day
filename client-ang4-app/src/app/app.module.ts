import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http"
import {RouterModule, Routes} from "@angular/router";

import {AppComponent} from './app.component';
import {SearchComponent} from './search/search.component';
import {IndexerComponent} from './indexer/indexer.component';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {SearchResultListComponent} from './search-result-list/search-result-list.component';
import {SearchItemResultComponent} from './search-item-result/search-item-result.component';
import {SearchService} from "./services/search.service";
import { SearchBoxComponent } from './search-box/search-box.component';
import {HttpClientModule} from "@angular/common/http";

const appRoutes:Routes = [
  {path: 'search', component: SearchComponent},
  {path: 'index', component: IndexerComponent},
  {path: '', redirectTo: '/search', pathMatch: 'full'},
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
    IndexerComponent,
    PageNotFoundComponent,
    SearchResultListComponent,
    SearchItemResultComponent,
    SearchBoxComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: true}
    )
  ],
  providers: [SearchService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
