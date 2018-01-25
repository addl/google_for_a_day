import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UrlInputBoxComponent } from './url-input-box.component';

describe('UrlInputBoxComponent', () => {
  let component: UrlInputBoxComponent;
  let fixture: ComponentFixture<UrlInputBoxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UrlInputBoxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UrlInputBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
