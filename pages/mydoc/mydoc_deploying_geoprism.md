---
title: Deploying Geoprism
keywords: geoprism, ansible, sas, docker
last_updated: July 3, 2016
tags: [getting_started]
summary: "Covers the basics of deploying Geoprism as a SAS product."
sidebar: mydoc_sidebar
permalink: mydoc_deploying_geoprism.html
folder: mydoc
---

TerraFrame uses Ansible scripts to deploy our platform. This allows us to automate all of the tedious tasks like installing Tomcat, Postgres, etc, and allows us to deploy production servers in predicable ways. Our Ansible scripts are open-source and can be found on our [geoprism-cloud](https://github.com/terraframe/geoprism-cloud) github repository.

Official releases of Geoprism are deployed by the TerraFrame team to our online Nexus repository (<http://nexus.terraframe.com>). You will need to obtain credentials to push to this Nexus server, however the public does have read-only access. By default our ansible scripts are set to use 'LATEST' (which is a Maven version keyword). When LATEST is sent to our nexus server, the server will send you the latest official release of Geoprism. We recommend, however, that you put a real version number so that you can keep track of what your current Geoprism version is, should there be any issues or incompatibilities with the latest Geoprism version.

If you want to do a Geoprism release from a local build you will have to modify the Ansible scripts yourself as this is not currently supported out-of-the box.

# Our Docker Containers

Our docker containers are pulled from our AWS ECS server. These containers automate for us all of the manual installation of Postgres, Tomcat, Ubuntu, etc. They also come pre-bundled with our SSL certificates. There is a public and a private flavour of our docker containers, the public contains a self-signed SSL certificate (which will display an SSL error when connecting, because its self-signed), and the private container comes with our private SSL geoprism.net wildcard certificate. The private docker container is only for usage on our Geoprism SAS® offering.

# The appcfg Directory
The appcfg directory contains configurations for your various Geoprism applications. For example, our appcfg directory looks like:

```
appcfg/
  demo1/
    geoprism/
      geoserver.properties
    runwaysdk/
      common.properties
      server.properties
    messages.properties
    project.json
  demo2/
    ... etc ...
```

These appcfg configuration files will override the default properties files that are included within Geoprism. This allows you to have lots of different deployments of Geoprism that are based on the same codebase, but configured differently.

# Deploying with Ansible
Here is how to deploy an existing Geoprism release to AWS using Ansible.

1. Make sure that ports 5432 and 22 are properly forwarded from the Ansible deployment machine to your remote production cloud server.
2. Check your inventory file. Make sure the settings are correct (don't want to wipe the production database now do we?).
3. On that note, back up your server.
4. Make sure your inventory file's permissions path points to the correct .pem key. Otherwise the SSH won't connect.
5. Set your appcfg_dir to point to the root of your appcfg directory.
6. Here's what a deployment command looks like: `ansible-playbook -i /path/to/inventory/files/dev.ini demo.yml`. Execute this command from the geoprism-cloud/ansible directory on your deployment machine (not the server itself). 

NOTE:
I was having issues using the latest ansible deployment so I cloned `a236cbf3b42fa2c51b89e9395b47abe286775829` and then ran `source ansible-dev/ansible/hacking/env-setup`. This will get around psycop2g errors.

{% include links.html %}
