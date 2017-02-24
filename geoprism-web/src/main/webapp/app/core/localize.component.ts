import { Input, Component, OnInit } from '@angular/core';

import { LocalizationService } from '../service/localization.service';

@Component({
  moduleId: module.id,
  selector: 'localize',
  templateUrl: 'localize.component.jsp',
  styleUrls: []
})
export class LocalizeComponent implements OnInit {
  @Input() key: string;
  text: string;
    
  constructor(private service: LocalizationService) { }

  ngOnInit(): void {
    this.text = this.service.decode(this.key);
  }
}
