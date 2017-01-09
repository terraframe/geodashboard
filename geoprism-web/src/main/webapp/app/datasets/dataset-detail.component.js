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
/// License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.
///
"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};
var core_1 = require("@angular/core");
var router_1 = require("@angular/router");
var common_1 = require("@angular/common");
var forms_1 = require("@angular/forms");
require("rxjs/add/operator/switchMap");
var dataset_1 = require("../model/dataset");
var core_service_1 = require("../service/core.service");
var dataset_service_1 = require("../service/dataset.service");
var DatasetResolver = (function () {
    function DatasetResolver(datasetService, eventService) {
        this.datasetService = datasetService;
        this.eventService = eventService;
    }
    DatasetResolver.prototype.resolve = function (route, state) {
        var _this = this;
        return this.datasetService.edit(route.params['id'])
            .catch(function (error) {
            _this.eventService.onError(error);
            return Promise.reject(error);
        });
    };
    return DatasetResolver;
}());
DatasetResolver = __decorate([
    __param(0, core_1.Inject(dataset_service_1.DatasetService)), __param(1, core_1.Inject(core_service_1.EventService)),
    __metadata("design:paramtypes", [dataset_service_1.DatasetService, core_service_1.EventService])
], DatasetResolver);
exports.DatasetResolver = DatasetResolver;
var DatasetDetailComponent = (function () {
    function DatasetDetailComponent(datasetService, router, route, location) {
        this.datasetService = datasetService;
        this.router = router;
        this.route = route;
        this.location = location;
        this.close = new core_1.EventEmitter();
        this.validName = true;
    }
    DatasetDetailComponent.prototype.ngOnInit = function () {
        this.dataset = this.route.snapshot.data['dataset'];
    };
    DatasetDetailComponent.prototype.validateName = function (name) {
        var _this = this;
        this.datasetService.validateDatasetName(name, this.dataset.id)
            .then(function (response) {
            _this.validName = true;
        })
            .catch(function (error) {
            _this.validName = false;
        });
    };
    DatasetDetailComponent.prototype.cancel = function () {
        var _this = this;
        this.datasetService.unlock(this.dataset)
            .then(function (response) {
            _this.goBack(_this.dataset);
        });
    };
    DatasetDetailComponent.prototype.onSubmit = function () {
        var _this = this;
        this.datasetService.apply(this.dataset)
            .then(function (dataset) {
            _this.goBack(dataset);
        });
    };
    DatasetDetailComponent.prototype.open = function (category, event) {
        this.router.navigate(['/category', category.id]);
    };
    DatasetDetailComponent.prototype.goBack = function (dataset) {
        this.close.emit(dataset);
        this.location.back();
    };
    return DatasetDetailComponent;
}());
__decorate([
    core_1.Input(),
    __metadata("design:type", dataset_1.Dataset)
], DatasetDetailComponent.prototype, "dataset", void 0);
__decorate([
    core_1.Output(),
    __metadata("design:type", Object)
], DatasetDetailComponent.prototype, "close", void 0);
__decorate([
    core_1.ViewChild('form'),
    __metadata("design:type", forms_1.NgForm)
], DatasetDetailComponent.prototype, "form", void 0);
DatasetDetailComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'dataset-detail',
        templateUrl: 'dataset-detail.component.jsp',
        styleUrls: []
    }),
    __metadata("design:paramtypes", [dataset_service_1.DatasetService,
        router_1.Router,
        router_1.ActivatedRoute,
        common_1.Location])
], DatasetDetailComponent);
exports.DatasetDetailComponent = DatasetDetailComponent;
//# sourceMappingURL=dataset-detail.component.js.map