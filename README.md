# dropwizard-dependency-reporting-resource

Add to your dropwizard app to get a report about the application and its environment.  For example, this could be used to monitor dependencies in use.

[![Build Status](https://travis-ci.org/willp-bl/dropwizard-dependency-reporting-resource.svg?branch=master)](https://travis-ci.org/willp-bl/dropwizard-dependency-reporting-resource)

## Example output

```json
{
  "datetime": 1512079678452,
  "system.hostname": "localmachine.local",
  "os.name": "Mac OS X",
  "os.arch": "x86_64",
  "os.version": "10.x",
  "runtime.vendor": "Oracle Corporation",
  "runtime.version": "1.8.0_x",
  "application.name": "mainClass",
  "application.version": "not set",
  "application.libraries": [
    "rt.jar",
    ...
    ]
}
```

## Using

This can be added to your app as a normal resource, or a dropwizard admin task.

### As a resource

```java
environment.jersey().register(DependencyReportingResource.class);
```

Then to get a report at runtime:

```bash
curl http://localhost:port/dependency-report
```

### As a dropwizard admin task

```java
environment.admin().addTask(new DependencyReportingTask());
```

Then to get a report at runtime:

```bash
curl -XPOST http://localhost:adminport/tasks/dependency-report
```

## Licence

[MIT Licence](LICENCE)