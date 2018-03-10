(ns summernightcalendar.core
  (:require [clojure.java.jdbc :as jdbc]
            [ring.adapter.jetty :as server]))

(defn getenv [env-name]
  (let [val (System/getenv env-name)]
    (if (nil? val) "" val)))

(defonce server (atom nil))

(def db-spec {:subprotocol "postgresql"
              :subname (getenv "POSTGRES_PATH")
              :user (getenv "POSTGRES_USER")
              :password (getenv "POSTGRES_PASSWORD")
              })

(defn ok [body]
  {:status 200
   :body body})

(defn html [res]
  (assoc res :headers {"Content-Type" "text/html; charset=utf-8"}))

(defn not-found []
  {:status 404
   :body "<h1>HTTP 404 : Page not found</h1>"})

(defn home-handler [res]
  {:status 200,
   :headers {"Content-Type" "text/plain"}
   :body "Hello, World"})

(defn login-handler [res]
  "Render login page and handling login event.")

(defn list-handler [res]
  "")

(def routes
  {"/" home-handler,
   "/login" login-handler,
   "/list" list-handler})

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
