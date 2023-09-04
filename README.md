# ID REST API

List of resources:

* KSUID
    * `/api/v1/ksuid`: typical KSUID
    * `/api/v1/ksuid/monotonic`: monotonic KSUID
    * `/api/v1/ksuid/subsecond`: subsecond KSUID
* TSID
    * `/api/v1/tsid`: typical  TSID
    * `/api/v1/tsid/int64`: decimal representation of a signed 64-bit integer
    * `/api/v1/tsid/uint64`: decimal representation of a unsigned 64-bit integer
* ULID
    * `/api/v1/ulid`: typical  ULID
    * `/api/v1/ulid/monotonic`: monotonic ULID
* UUID
    * `/api/v1/uuid/v1`: time-based UUID
    * `/api/v1/uuid/v3`: name-based using MD5 UUID
        * Query parameters:
            * `namespace`: a pre-defined or custom namespace UUID
            * `name`: an arbitrary string
    * `/api/v1/uuid/v4`: random-based UUID
    * `/api/v1/uuid/v5`: name-based UUID using SHA-1
        * Query parameters:
            * `namespace`: a pre-defined or custom namespace UUID
            * `name`: an arbitrary string
    * `/api/v1/uuid/v6`: time-based reordered UUID
    * `/api/v1/uuid/v7`: time-based UUID using Unix Epoch


List of pre-defined namespace aliases:

* `dns`: 6ba7b810-9dad-11d1-80b4-00c04fd430c8
    * Example: `/api/v1/uuid/v5?namespace=dns&name=www.example.com`
* `url`: 6ba7b811-9dad-11d1-80b4-00c04fd430c8
    * Example: `/api/v1/uuid/v5?namespace=url&name=www.example.com/my-page`
* `oid`: 6ba7b812-9dad-11d1-80b4-00c04fd430c8
    * Example: `/api/v1/uuid/v5?namespace=oid&name=1.3.6.1`
* `x500`: 6ba7b814-9dad-11d1-80b4-00c04fd430c8
    * Example: `/api/v1/uuid/v5?namespace=x500&name=url-encoded-distinguished-name`



