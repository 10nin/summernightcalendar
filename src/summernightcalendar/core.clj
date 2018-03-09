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

;;-- record definition
(defrecord group- [group-name password])
(defrecord group-activity- [group active-day active-type])
(defrecord park-caendar- [opening-day opening-type])

;;---- Insert functions ----
(defn insert-group [group]
  "Insert group information into GROUP_MASTER and LOGIN_INFORMATION_MASTER.")

(defn insert-park-calendar [park-caendar]
  "Insert park infromation into PARK_CALENDAR_MASTER.")
  
(defn insert-group-activity [group-activity]
  "Insert group activity day into GROUP_CALENDAR")

;;---- Select functions ----
(defn select-month-calendar [month]
  "Select :month calendar from GROUP_CALENDAR")

(defn select-group-calendar [group]
  "Select :group calendar from GROUP_CALENDAR")

;;---- Web page handler ----
(defn login-page-handler []
  "Render login page and handling login event.")

(defn monthly-calendar-handler [month]
  "Render :month calendar page")
