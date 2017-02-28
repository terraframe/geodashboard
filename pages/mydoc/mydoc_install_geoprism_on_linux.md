---
title: Install GeoPrism on Ubuntu Linux
permalink: mydoc_install_geoprism_on_linux.html
keywords: geoprism on linux
summary: "GeoPrism is primarily used in production on Linux servers and so works well in a Linux development environment as well."
sidebar: mydoc_sidebar
folder: mydoc
---

{% include tip.html content="For more reliable compatibility use Eclipse IDE (Luna)." %}

### 1. Download RunwaySDK

1.  Download or clone the RunwaySDK project from the [Github repo](https://github.com/terraframe/Runway-SDK.git).
2.  Import RunwaySDK into you Eclipse environment.

### 2. Download RunwaySDK-GIS

1.  Download or clone the RunwaySDK-GIS project from the [Github repo](https://github.com/terraframe/Runway-SDK-GIS.git).
2.  Import RunwaySDK-GIS into you Eclipse environment.

### 3. Download GeoPrism

1.  Download or clone the GeoPrism project from the [Github repo](https://github.com/terraframe/geoprism.git).
2.  Import GeoPrism into you Eclipse environment.

### 4. Install PostgreSQL 9.0+ and PostGIS 2.0+

1.  Install PostgreSQL 9.0 + using whatever method you prefer.
2.  After installing PostgreSQL create a database called "template-postgis".  
3.  Spatially enable the template database by installing PostGIS with the SQL command:

```
CREATE EXTENSION postgis;
```

### 5. Install Oracle Java 8

If you don't already have it installed you should install the Oracle version of Java 8.  The Oracle version is faster than the open source competitors.

### 6. Install Maven

1.  Install Maven with the following terminal command:

```
sudo apt-get install maven
```





{% include links.html %}
