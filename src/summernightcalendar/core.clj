(ns summernightcalendar.core
  (:require [clojure.java.jdbc :as jdbc]))

(defn getenv [env-name]
  (let [val (System/getenv env-name)]
    (if (nil? val) "" val)))

(def db-spec {:subprotocol "postgresql"
              :subname (getenv "POSTGRES_PATH")
              :user (getenv "POSTGRES_USER")
              :password (getenv "POSTGRES_PASSWORD")
              })
