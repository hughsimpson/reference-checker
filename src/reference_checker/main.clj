(ns reference_checker.main
  (:require [clojure.java.io :as io])
  (:gen-class))
;;raw data
(def data
;  (with-open [mapping-file (io/reader "resources/mappings.txt")
;              workflow-file (io/reader "resources/workflow.xml")]
    (let [mapping-file (slurp "resources/mappings.txt")
          workflow-file (slurp "resources/workflow.xml")
          mapping_regex #"\(\s*[^)(]+\s+/[^)(/]*/[^)(/]*/Sphonic/([^)(]+)\s*\)"
          mapping_matches (re-seq mapping_regex mapping-file)
          mapping_refs (map #(nth % 1) mapping_matches)
          orphan_refs (filter #(not (.contains workflow-file %)) mapping_refs)
          no_of_fact_refs (count mapping_matches)]
      (println no_of_fact_refs)
      orphan_refs))
;; main function
(defn -main [] (println data) (println (count data)))