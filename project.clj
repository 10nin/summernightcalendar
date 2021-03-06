(defproject summernightcalendar "0.1.0-SNAPSHOT"
  :description "Schedule arrangemnet web application for my hobby group."
  :url "https://gitlab.com/10nin/summernightcalendar"
  :license {:name "MIT Licnese"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring "1.6.3"]
                 [compojure "1.6.0"]
                 [selmer "1.11.7"]
                 [org.clojure/java.jdbc "0.7.5"]
                 [org.postgresql/postgresql "42.2.1"]])
