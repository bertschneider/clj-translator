(ns de.herrnorbert.utils.url-utils
  "Some utilities to generate URL strings."
  (:gen-class)
  (:use [de.herrnorbert.utils.map-utils :only [concat-map-entries]])
  (:import (java.net URLEncoder)))

; --- URL building ---
(defn key-name [k]
  "If k is a keyword its name is returned otherwise k."
  (if (keyword? k)
    (name k)
    k))

(defn params-string [params]
  "Creates the param part of an url out of the given map."
  (if-let [entries (not-empty
                    (concat-map-entries params "=" key-name #(URLEncoder/encode %)))]
    (str "?" (reduce #(str %1 "&" %2) entries))))

(defn build-url [base params]
  "Builds an URL out of the given base url and parameter map."
  (str base (params-string params)))
