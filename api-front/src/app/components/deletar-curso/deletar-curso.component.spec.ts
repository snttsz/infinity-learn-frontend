import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletarCursoComponent } from './deletar-curso.component';

describe('DeletarCursoComponent', () => {
  let component: DeletarCursoComponent;
  let fixture: ComponentFixture<DeletarCursoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeletarCursoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeletarCursoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
