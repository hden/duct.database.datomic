# duct.database.datomic

Integrant methods for connecting to a Datomic Cloud database.

Heavily inspired by the awesome [duct.database.sql](https://github.com/duct-framework/database.sql) library.

## Related Projects

- https://github.com/hden/duct.module.datomic - A Duct module that adds Integrant keys for a Datomic database connection and Ragtime migrations to a configuration.

## Installation

To install, add the following to your project dependencies:

```
[hden/duct.database.datomic "0.1.0"]
```

## Usage

This library provides two things: a `Boundary` record that holds a database client and a database connection, and a multimethod for `:duct.database/datomic` that initiates a database spec into the Boundary.

```clojure
;; https://docs.datomic.com/client-api/datomic.client.api.html#var-client
{:duct.database/datomic {:server-type :ion
                         :region "AWS region"
                         :system "your system name"
                         :endpoint "http://entry.{{system}}.{{region}}.datomic.net:8182"
                         :proxy-port 8182
                         :database "db-name"}}
```

When you write functions against the database, consider using a protocol and extending the `Boundary` record. This will allow you to easily mock or stub out the database using a tool like [Shrubbery](https://github.com/bguthrie/shrubbery).

## License

Copyright © 2020 Haokang Den

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.
