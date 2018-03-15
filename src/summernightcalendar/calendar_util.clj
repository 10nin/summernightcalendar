(ns summernightcalendar.calendar-util)

(defn get-date
  ([f calendar field] (f (.get calendar field)))
  ([calendar field] (get-date identity calendar field)))

(defn get-year [calendar]
  (get-date calendar java.util.Calendar/YEAR))

(defn get-month [calendar]
  (get-date inc calendar java.util.Calendar/MONTH))

(defn get-day [calendar]
  (get-date calendar java.util.Calendar/DAY_OF_MONTH))

(defn get-first-day [year month]
  "Get java.util.Calendar object of year/month/1."
  (doto (java.util.Calendar/getInstance)
    (.set java.util.Calendar/YEAR year)
    (.set java.util.Calendar/MONTH (dec month))
    (.set java.util.Calendar/DATE 1)))

(defn get-last-day [calendar]
  "Get last day at month of calendar."
  (doto (get-first-day (get-year calendar) (inc (get-month calendar)))
    (.add java.util.Calendar/DATE -1)))

(defn get-monthly-days [last-day]
  (range 1 (inc (get-day last-day))))

(defn get-alignment-spaces [first-day]
  "Get alignment balnk space list for calendar output."
  (let [w (dec (.get first-day java.util.Calendar/DAY_OF_WEEK))]
    (for [x (range (mod w 7))] "")))
