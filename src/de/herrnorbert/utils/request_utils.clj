(ns de.herrnorbert.utils.request-utils
  "Some functions to handle web requests."
  (:gen-class)
  (:use [clojure.contrib.http.agent :only [http-agent string]]
        [clojure.contrib.json.read]))

(defn request-url [url]
  "Returns the content of the given url."
  (->> url
       (http-agent)
       (string)))

(defn request-json-url [url]
  "Invokes the given url and converts the returned json string to clojure data structures."
  (->> url
       (request-url)
       (read-json-string)))
