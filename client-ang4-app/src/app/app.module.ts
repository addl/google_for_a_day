import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http"
import {RouterModule, Routes} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";

import {AppComponent} from './app.component';
import {SearchComponent} from './search/search.component';
import {IndexerComponent} from './indexer/indexer.component';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {SearchResultListComponent} from './search/search-result-list/search-result-list.component';
import {SearchItemResultComponent} from './search/search-result-list/search-item-result/search-item-result.component';
import {SearchBoxComponent} from './search/search-box/search-box.component';
import {SearchService} from "./services/search.service";
import {IndexService} from "./services/index.service";
import { UrlInputBoxComponent } from './indexer/url-input-box/url-input-box.component';
// import {CommonModule} from "@angular/common";

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
    SearchBoxComponent,
    UrlInputBoxComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    // CommonModule,
    HttpClientModule,
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: true}
    )
  ],
  providers: [SearchService, IndexService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
