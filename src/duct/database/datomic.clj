(ns duct.database.datomic
  (:require [integrant.core :as ig]
            [datomic.client.api :as datomic]
            [datomic.client.api.protocols :as client-protocols]))

(defrecord Boundary [client connection])

(defn- connect-ensure-database
  "Ensure that a database named db-name exists. Returns a connection."
  [client db-name]
  {:pre [(and (satisfies? client-protocols/Client client)
              (string? db-name))]}
  (when-not (contains? (into #{} (datomic/list-databases client {}))
                       db-name)
    (datomic/create-database client {:db-name db-name}))
  (datomic/connect client {:db-name db-name}))

(defmethod ig/init-key :duct.database/datomic
  [_ {:keys [database] :as options}]
  (let [client (datomic/client (dissoc options :database))
        connection (when database (connect-ensure-database client database))]
    (->Boundary client connection)))
