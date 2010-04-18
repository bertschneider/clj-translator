(ns de.herrnorbert.utils.map-utils
  "Some map utilities."
  (:gen-class))

(defn map-entries [f m]
  "Invokes f for every entry in m providing the key and value of the entry as parameters.
  (map-entries #(%1 \"=\" %2) {:key1 value1 :key2 value2})"
  (map (fn [[k v]] (f k v)) m))

(defn concat-map-entries
  ([m]
     "Maps the entries of m to key=value strings."
     (concat-map-entries m "=" #(%) #(%)))
  ([m s kf vf]
     "Concats the entries of m via s. 
	 kf can be used to transform the key, vf to transform the value."
     (map-entries #(str (kf %1) s (vf %2)) m)))
