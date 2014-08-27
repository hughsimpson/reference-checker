(ns reference_checker.main
  (:require [clojure.java.io :as io])
  (:gen-class))
;;raw data
(def data
;  (with-open [mapping-file (io/reader "resources/mappings.txt")
;              workflow-file (io/reader "resources/workflow.xml")]
    (let [mapping-file (slurp "resources/mappings.txt")
          workflow-file (slurp "resources/workflow.xml")
          functions-file (slurp "resources/functions1.txt")
          mapping_regex #"\(\s*[^)(]+\s+/[^)(/]*/[^)(/]*/Sphonic/([^)(]+)\s*\)"
          mapping_matches (re-seq mapping_regex mapping-file)
          mapping_refs (distinct (map #(nth % 1) mapping_matches))
          orphan_refs (->> mapping_refs
                        (filter #(not (.contains functions-file (str "$"%))) ,,,)
                        (filter #(not (.contains workflow-file %)) ,,,))
          no_of_fact_refs (count mapping_matches)]
      (println no_of_fact_refs)
      orphan_refs))
;; main function
(defn -main [] (println data) (println (count data)))