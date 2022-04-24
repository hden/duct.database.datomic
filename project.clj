(defproject hden/duct.database.datomic "0.2.0"
  :description "Integrant methods for connecting to a Datomic Cloud database"
  :url "https://github.com/hden/duct.database.datomic"
  :license {:name "EPL-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :repositories [["datomic-cloud" {:url "s3://datomic-releases-1fc2183a/maven/releases"}]]
  :managed-dependencies [[com.datomic/client-cloud "1.0.120"]]
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [integrant "0.8.0"]
                 [com.datomic/client-cloud]]
  :repl-options {:init-ns duct.database.datomic})
