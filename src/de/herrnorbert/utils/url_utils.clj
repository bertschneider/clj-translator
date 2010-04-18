(ns de.herrnorbert.utils.url-utils
  "Some utilities to generate URL strings."
  (:gen-class)
  (:use [clojure.contrib.str-utils :only [re-gsub]]
        [de.herrnorbert.utils.map-utils :only [concat-map-entries]]))

; --- Functions to encode url strings ---
(def url-enc [[#"%" "%25"]
              [#";" "%3B"]
              [#"\?""%3F"]
              [#"/" "%2F"]
              [#":" "%3A"]
              [#"#" "%23"]
              [#"&" "%26"]
              [#"=" "%3D"]
              [#"\+""%2B"]
              [#"\$""%24"]
              [#"," "%2C"]
              [#" " "%20"]
              [#"<" "%3C"]
              [#">" "%3E"]
              [#"~" "%7E"]
              [#"\|" "%7C"]])

(defn rep-url-char [a [k v]]
  "Replaces all occurrences of k in a with v."
  (re-gsub k v (str a)))

(defn encode-url-str [s]
  "Replaces special characters in s with their appropriate url format"
  (reduce rep-url-char s url-enc))


; --- URL building ---
(defn key-name [k]
  "If k is a keyword its name is returned otherwise k."
  (if (keyword? k)
    (name k)
    k))

(defn params-string [params]
  "Creates the param part of an url out of the given map."
  (if-let [entries (not-empty
                    (concat-map-entries params "=" key-name encode-url-str))]
    (str "?" (reduce #(str %1 "&" %2) entries))))

(defn build-url [base params]
  "Builds an URL out of the given base url and parameter map."
  (str base (params-string params)))
