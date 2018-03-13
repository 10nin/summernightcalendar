(ns summernightcalendar.calendar-util)

(defn get-date [f calendar field]
  (f (.get calendar field)))

(defn get-year [calendar]
  (get-date identity calendar java.util.Calendar/YEAR))

(defn get-month [calendar]
  (get-date inc calendar java.util.Calendar/MONTH))

(defn get-day [calendar]
  (get-date identity calendar java.util.Calendar/DAY_OF_MONTH))

(defn get-first-day [year month]
  "Get java.util.Calendar object of year/month/1."
  (let [m (java.util.Calendar/getInstance)]
    (.set m java.util.Calendar/YEAR year)
    (.set m java.util.Calendar/MONTH (dec month))
    (.set m java.util.Calendar/DATE 1)
    m))

(defn get-last-day [calendar]
  "Get last day at month of calendar."
  (let [c (get-first-day (get-year calendar) (get-month calendar))]
    (.add c java.util.Calendar/DATE -1)
    c))
