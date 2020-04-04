(defproject duct.database.datomic "0.1.0-SNAPSHOT"
  :description "Integrant methods for connecting to a Datomic Cloud database"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :repositories [["datomic-cloud" {:url "s3://datomic-releases-1fc2183a/maven/releases"}]]
  :managed-dependencies [[com.datomic/client-cloud "0.8.80"]]
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [integrant "0.3.3"]
                 [com.datomic/client-cloud]]
  :repl-options {:init-ns duct.database.datomic})
