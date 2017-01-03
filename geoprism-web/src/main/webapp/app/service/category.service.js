/*
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.
 */
"use strict";
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var http_1 = require('@angular/http');
require('rxjs/add/operator/toPromise');
var basic_service_1 = require('./basic.service');
var CategoryService = (function (_super) {
    __extends(CategoryService, _super);
    function CategoryService(http) {
        _super.call(this);
        this.http = http;
    }
    //    
    //    service.createOption = function(connection, option) {
    //      var request = runwayService.createConnectionRequest(connection);
    //      
    //      net.geoprism.ontology.ClassifierController.createOption(request, option);
    //    }
    //    
    //    service.deleteOption = function(connection, id) {
    //      var request = runwayService.createConnectionRequest(connection);
    //      
    //      net.geoprism.ontology.ClassifierController.deleteOption(request, id);
    //    }
    //    
    //    service.applyOption = function(connection, config) {
    //      var request = runwayService.createConnectionRequest(connection);
    //      
    //      net.geoprism.ontology.ClassifierController.applyOption(request, config);
    //    }
    //    
    //    service.updateCategory = function(connection, category) {
    //      var request = runwayService.createConnectionRequest(connection);
    //      
    //      net.geoprism.ontology.ClassifierController.updateCategory(request, category);
    //    }
    CategoryService.prototype.getAll = function () {
        return this.http
            .get(acp + '/category/all')
            .toPromise()
            .then(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    CategoryService.prototype.edit = function (id) {
        var headers = new http_1.Headers({
            'Content-Type': 'application/json'
        });
        return this.http
            .post(acp + '/category/edit', JSON.stringify({ id: id }), { headers: headers })
            .toPromise()
            .then(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    CategoryService.prototype.get = function (id) {
        var headers = new http_1.Headers({
            'Content-Type': 'application/json'
        });
        return this.http
            .post(acp + '/category/get', JSON.stringify({ id: id }), { headers: headers })
            .toPromise()
            .then(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    CategoryService.prototype.unlock = function (category) {
        var headers = new http_1.Headers({
            'Content-Type': 'application/json'
        });
        return this.http
            .post(acp + '/category/unlock', JSON.stringify({ id: category.id }), { headers: headers })
            .toPromise()
            .catch(this.handleError);
    };
    CategoryService.prototype.apply = function (category) {
        var headers = new http_1.Headers({
            'Content-Type': 'application/json'
        });
        return this.http
            .post(acp + '/category/apply', JSON.stringify({ category: category }), { headers: headers })
            .toPromise()
            .then(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    CategoryService.prototype.remove = function (category) {
        var headers = new http_1.Headers({
            'Content-Type': 'application/json'
        });
        return this.http
            .post(acp + '/category/remove', JSON.stringify({ id: category.id }), { headers: headers })
            .toPromise()
            .catch(this.handleError);
    };
    CategoryService.prototype.validate = function (name, id) {
        var params = new http_1.URLSearchParams();
        params.set('name', name);
        params.set(id, id);
        return this.http
            .get(acp + '/category/validate', params)
            .toPromise()
            .catch(this.handleError);
    };
    CategoryService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], CategoryService);
    return CategoryService;
}(basic_service_1.BasicService));
exports.CategoryService = CategoryService;
//# sourceMappingURL=category.service.js.map