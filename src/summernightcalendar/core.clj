(ns summernightcalendar.core
  (:require [clojure.java.jdbc :as jdbc]
            [compojure.core :refer [defroutes context GET]]
            [compojure.route :as route]
            [selmer.parser :as tmpl]
            [ring.adapter.jetty :as server]
            [ring.util.response :as res]
            [summernightcalendar.calendar-util :refer :all]))

(defn getenv [env-name]
  (let [val (System/getenv env-name)]
    (if (nil? val) "" val)))

(defonce server (atom nil))

(def db-spec {:subprotocol "postgresql"
              :subname (getenv "POSTGRES_PATH")
              :user (getenv "POSTGRES_USER")
              :password (getenv "POSTGRES_PASSWORD")
              })

(defn html [res]
  (res/content-type res "text/html; charset=utf-8"))

(defn calendar-view [res]
  "This is a schedule list page.")

(defn home-handler [res]
  (-> (res/response "Hello, World")
      html))

(defn login-handler [req]
  "Render login page and handling login event."
  (-> (res/response "This is a login page.")
      html))

(defn list-handler [req]
  ""
  (-> (calendar-view req)
      res/response
      html))

(defroutes handler
  (GET "/" req home-handler)
  (GET "/login" req login-handler)
  (GET ["/list/:month" :month #"[0-9]+"] req list-handler)
  (route/not-found "<h1>HTTP 404 : Page not found</h1>"))

(defn match-route [uri]
  (get handler uri))

(defn start-server []
  (when-not @server
    (reset! server (server/run-jetty #'handler {:port 3000 :join? false}))))

(defn stop-server []
  (when @server
    (.stop @server)
    (reset! server nil)))

(defn restart-server []
  (when @server
    (stop-server)
    (start-server)))

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

(defn monthly-calendar-handler [month]
  "Render :month calendar page")
