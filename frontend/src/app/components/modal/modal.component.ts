import {Component, ElementRef, OnDestroy, OnInit} from '@angular/core';
import * as $ from 'jquery';
@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnInit,OnDestroy {

    element: JQuery;

  constructor(private el: ElementRef) {

      this.element = $(el.nativeElement);
      this.element.hide();
  }

  ngOnInit() {
      this.element.appendTo('body');
  }


    ngOnDestroy(): void {
      this.close();
      this.element.remove();
    }

    open(): void {
        this.element.show();
        $('body').addClass('modal-open');
    }

    // close modal
    close(): void {
        this.element.hide();
        $('body').removeClass('modal-open');
    }
}
