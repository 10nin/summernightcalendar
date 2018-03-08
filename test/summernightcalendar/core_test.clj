(ns summernightcalendar.core-test
  (:require [clojure.test :refer :all]
            [summernightcalendar.core :refer :all]))

(deftest get-environment-value-test
  (testing "get-environment-value"
    (is (= (summernightcalendar.core/getenv "TERM") "xterm"))))
