# voxmachina/itstore

An abstraction (PostRepo) of a post repository for the indieweb-toolkit project and others.

Currently provides a local in-memory repository (PostRepoMemory) and a filesystem based repository (PostRepoFS).

See unit tests and core.clj to learn how to use in your projects. More usage instructions below.

## Usage

### Run tests

Run the project's tests:

```bash
$ clj -T:build test
```

### Run continuous integration and create jar

```bash
$ clj -T:build ci
```

### Install jar file locally (mvn)

In order to use the this library as a dependency in your project you can clone locally and intall into mvn as a local jar.

```bash
$ clj -T:build install
```
