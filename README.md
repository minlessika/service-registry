<img src="https://www.minlessika.com/com/webviewer/img/logo.svg" width="64px" height="64px"/>

[![EO principles respected here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org)
[![DevOps By Rultor.com](http://www.rultor.com/b/minlessika/service-registry)](http://www.rultor.com/p/minlessika/service-registry)
[![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

[![Javadoc](http://www.javadoc.io/badge/com.minlessika.erp/service-registry.svg)](http://www.javadoc.io/doc/com.minlessika.erp/service-registry)
[![License](https://img.shields.io/badge/License-Minlessika-important.svg)](https://github.com/minlessika/service-registry/blob/master/LICENSE.txt)
[![codecov](https://codecov.io/gh/minlessika/service-registry/branch/master/graph/badge.svg)](https://codecov.io/gh/minlessika/service-registry)
[![Hits-of-Code](https://hitsofcode.com/github/minlessika/service-registry)](https://hitsofcode.com/view/github/minlessika/service-registry)
[![Maven Central](https://img.shields.io/maven-central/v/com.minlessika.erp/service-registry.svg)](https://maven-badges.herokuapp.com/maven-central/com.minlessika.erp/service-registry)
[![PDD status](http://www.0pdd.com/svg?name=minlessika/service-registry)](http://www.0pdd.com/p?name=minlessika/service-registry)

Service that helps to discover links of other services.

## How to use it
Simply make this query:
```shell
/GET /service/<service key>/link
```

And you will get this response:
```shell
HTTP/1.1 200 OK
{
  "link": "<service link>"
}
```
