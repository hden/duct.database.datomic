(defproject hden/duct.database.datomic "0.3.1"
  :description "Integrant methods for connecting to a Datomic Cloud database"
  :url "https://github.com/hden/duct.database.datomic"
  :license {:name "EPL-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :repositories [["datomic-cloud" {:url "s3://datomic-releases-1fc2183a/maven/releases"}]]
  :managed-dependencies [[com.datomic/client-cloud "1.0.130"]]
  :dependencies [[org.clojure/clojure "1.12.0"]
                 [com.datomic/client-cloud]
                 [diehard "0.11.12"]
                 [integrant "0.13.0"]]
  :repl-options {:init-ns duct.database.datomic})
