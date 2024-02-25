ID API
========================

A REST API for generating IDs.

Resources
------------------------

List of resources:

* UUID
    * `/id-api/uuid/v1`: time-based UUID
    * `/id-api/uuid/v3`: name-based using MD5 UUID
        * Query parameters:
            * `namespace`: a pre-defined or custom namespace UUID
            * `name`: an arbitrary string
    * `/id-api/uuid/v4`: random-based UUID
    * `/id-api/uuid/v5`: name-based UUID using SHA-1
        * Query parameters:
            * `namespace`: a pre-defined or custom namespace UUID
            * `name`: an arbitrary string
    * `/id-api/uuid/v6`: time-based reordered UUID
    * `/id-api/uuid/v7`: time-based UUID using Unix Epoch
* ULID
    * `/id-api/ulid`: typical  ULID
    * `/id-api/ulid/monotonic`: monotonic ULID
* KSUID
    * `/id-api/ksuid`: typical KSUID
    * `/id-api/ksuid/monotonic`: monotonic KSUID
    * `/id-api/ksuid/subsecond`: subsecond KSUID
* TSID
    * `/id-api/tsid`: typical  TSID
    * `/id-api/tsid/int64`: decimal representation of a signed 64-bit integer
    * `/id-api/tsid/uint64`: decimal representation of a unsigned 64-bit integer

Parameters
------------------------

List of query parameters:

* `n`: number of IDs to generate in the current request (exept UUIDv3 and UUIDv5).
* `namespace`: a pre-defined or custom namespace UUID (only UUIDv3 and UUIDv5).
* `name`: an arbitrary string (only UUIDv3 and UUIDv5).

The variable environment varible `ID_API_LIMIT` controls the maximum number of IDs that can be generated per request.

Content types
------------------------

List of response content types:

* `text/plain`: if the header `Accept: text/plain` is present in the request.
* `application/json`: if the header `Accept: text/plain` is *not* present in the request.

The IDs in the plain text response are separated by `\n` (line feed) character.

Namespaces
------------------------

List of pre-defined namespace aliases:

* `dns`: 6ba7b810-9dad-11d1-80b4-00c04fd430c8
    * Example: `/id-api/uuid/v5?namespace=dns&name=www.example.com`
* `url`: 6ba7b811-9dad-11d1-80b4-00c04fd430c8
    * Example: `/id-api/uuid/v5?namespace=url&name=www.example.com/my-page`
* `oid`: 6ba7b812-9dad-11d1-80b4-00c04fd430c8
    * Example: `/id-api/uuid/v5?namespace=oid&name=1.3.6.1`
* `x500`: 6ba7b814-9dad-11d1-80b4-00c04fd430c8
    * Example: `/id-api/uuid/v5?namespace=x500&name=url-encoded-distinguished-name`

Usage
------------------------

Generate a UUIDv4 in JSON:

```bash
wget --quiet --output-document=/dev/stdout "http://localhost:8080/id-api/uuid/v4" && echo;
```
```javascript
["e438dadf-f754-4421-956c-375ef8007d16"]
```

Generate a UUIDv3 for the DNS `www.example.com` in JSON:

```bash
wget --quiet --output-document=/dev/stdout "http://localhost:8080/id-api/uuid/v3?namespace=dns&name=www.example.com" && echo
```
```javascript
["5df41881-3aed-3515-88a7-2f4a814cf09e"]
```

Generate 5 UUIDv7 in plain text:

```bash
wget --quiet --header="Accept: text/plain" --output-document=/dev/stdout "http://localhost:8080/id-api/uuid/v7?n=5" && echo;
```
```
018de213-73ed-7dcd-b601-c055ad0bf196
018de213-73ed-7dcd-b602-6f15ee498e14
018de213-73ed-7dcd-b603-6e0721690d48
018de213-73ed-7dcd-b604-25ab35645794
018de213-73ed-7dcd-b605-671d04f3b0ce
```

Docker
------------------------

Build an image and run on it on a container:

```
./docker.sh
```

List the build image:

```
docker image ls
REPOSITORY       TAG       IMAGE ID       CREATED          SIZE
example/id-api   latest    61a0b5dbede8   13 minutes ago   344MB
```

List the running container:

```
docker container ls
CONTAINER ID   IMAGE            COMMAND                  CREATED          STATUS          PORTS                                       NAMES
7be12cd2c8d6   example/id-api   "/__cacert_entrypoin…"   14 minutes ago   Up 14 minutes   0.0.0.0:8080->8080/tcp, :::8080->8080/tcp   id-api
```

License
------------------------

This program is Open Source software released under the [MIT license](https://opensource.org/licenses/MIT).


