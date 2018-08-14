///
/// Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
///
/// This file is part of Runway SDK(tm).
///
/// Runway SDK(tm) is free software: you can redistribute it and/or modify
/// it under the terms of the GNU Lesser General Public License as
/// published by the Free Software Foundation, either version 3 of the
/// License, or (at your option) any later version.
///
/// Runway SDK(tm) is distributed in the hope that it will be useful, but
/// WITHOUT ANY WARRANTY; without even the implied warranty of
/// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
/// GNU Lesser General Public License for more details.
///
/// You should have received a copy of the GNU Lesser General Public
/// License along with Runway SDK(tm).  If not, see <ehttp://www.gnu.org/licenses/>.
///

import { Injectable } from '@angular/core';
import { Headers, Http, Response, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { EventService, BasicService } from '../core/service/core.service';
import { EventHttpService } from '../core/service/event-http.service';

import { Profile } from './profile';

declare var acp: any;

@Injectable()
export class ProfileService extends BasicService {
  
  constructor(service: EventService, private ehttp: EventHttpService, private http: Http) {
    super(service); 
  }
  
  get(): Promise<Profile> {

    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
  
    return this.ehttp
      .post(acp + '/account/get', {headers: headers})
      .toPromise()
      .then((response: any) => {
        return response.json() as Profile;
      })
      .catch(this.handleError.bind(this));      
  }  
  
  
  apply(profile:Profile): Promise<Profile> {
    
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
    .post(acp + '/account/apply', JSON.stringify({account:profile}), {headers: headers})
    .toPromise()
    .then((response: any) => {
      return response.json() as Profile;
    })
    .catch(this.handleError.bind(this));      
  }  
  
  unlock(oid:string): Promise<Response> {
    
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
    .post(acp + '/account/unlock', JSON.stringify({oid:oid}), {headers: headers})
    .toPromise()
    .catch(this.handleError.bind(this));      
  }  
}
