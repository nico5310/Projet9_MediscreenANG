import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NoteAddComponent } from './components/note-add/note-add.component';
import { NoteListComponent } from './components/note-list/note-list.component';
import { NoteUpdateComponent } from './components/note-update/note-update.component';
import { PatientListComponent } from './components/patient-list/patient-list.component';
import { PatientAddComponent } from './components/patient-add/patient-add.component';
import { PatientUpdateComponent } from './components/patient-update/patient-update.component';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { PatientService } from './services/patient.service';
import {HttpClientModule} from "@angular/common/http";

export const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'note/add', component: NoteAddComponent},
  {path: 'note/list/:patientId', component: NoteListComponent},
  {path: 'note/update/:id', component: NoteUpdateComponent},
  {path: 'patient/add', component: PatientAddComponent},
  {path: 'patient/list', component: PatientListComponent},
  {path: 'patient/update/:id', component: PatientUpdateComponent},
]

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NotFoundComponent,
    NoteAddComponent,
    NoteListComponent,
    NoteUpdateComponent,
    PatientListComponent,
    PatientAddComponent,
    PatientUpdateComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [PatientService],
  bootstrap: [AppComponent]
})
export class AppModule { }
