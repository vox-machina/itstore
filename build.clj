(ns build
  (:refer-clojure :exclude [test])
  (:require [clojure.tools.build.api :as b]))

(def major 0)
(def minor 1)
(def version (format "%d.%d.%s" major minor (b/git-count-revs nil)))
(def version-file "version.edn")

(defn test "Run all the tests." [opts]
  (let [basis    (b/create-basis {:aliases [:test]})
        cmds     (b/java-command
                  {:basis      basis
                    :main      'clojure.main
                    :main-args ["-m" "cognitect.test-runner"]})
        {:keys [exit]} (b/process cmds)]
    (when-not (zero? exit) (throw (ex-info "Tests failed" {}))))
  opts)

(defn build
  "builds indieweb-toolkit store - taking care of setup, version files etc"
  [_]
  (println "building...")
  (println "version: " version)
  (b/write-file {:path version-file :content {:indieweb-toolkit-store version}}))
