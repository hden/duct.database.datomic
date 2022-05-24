(ns duct.database.datomic
  (:require [cognitect.anomalies :as anomalies]
            [datomic.client.api :as datomic]
            [datomic.client.api.protocols :as client-protocols]
            [diehard.core :as diehard]
            [integrant.core :as ig]))

(def retryable-anomaly?
  "Set of retryable anomalies."
  #{::anomalies/busy
    ::anomalies/unavailable
    ::anomalies/interrupted})

(defn- retry? [_ e]
  (-> e ex-data ::anomalies/category retryable-anomaly?))

(def default-retry-policy
  {:backoff-ms    [10 1000]
   :jitter-factor 0.25
   :max-retries   5
   :retry-if      retry?})

(defrecord Boundary [client connection])

(defn- connect-ensure-database
  "Ensure that a database named db-name exists. Returns a connection."
  [{:keys [client db-name retry-policy]}]
  {:pre [(and (satisfies? client-protocols/Client client)
              (string? db-name))]}
  (when-not (contains? (into #{} (datomic/list-databases client {}))
                       db-name)
    (datomic/create-database client {:db-name db-name}))
  (diehard/with-retry retry-policy
    (datomic/connect client {:db-name db-name})))

(defmethod ig/init-key :duct.database/datomic
  [_ {:keys [database retry-policy]
      :or {retry-policy default-retry-policy}
      :as options}]
  (let [client (datomic/client (dissoc options :database))
        connection (when database (connect-ensure-database {:client client
                                                            :database database
                                                            :retry-policy retry-policy}))]
    (->Boundary client connection)))
