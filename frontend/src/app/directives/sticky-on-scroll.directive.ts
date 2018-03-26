import {Directive, ElementRef, Input, HostListener} from '@angular/core';

@Directive({
  selector: '[styckyOnScroll]'
})
export class StickyOnScrollDirective {

  @Input()
  styckyOnScroll = 100;

  constructor(private element: ElementRef) {
  }

@HostListener('window:scroll', ['$event'])
  handleScrollEvent(e) {

    if (window.pageYOffset > this.styckyOnScroll) {
      setTimeout(()=>{
        this.element.nativeElement.classList.add('stick');
      })
    } else {
      this.element.nativeElement.classList.remove('stick');
    }
  }

}
