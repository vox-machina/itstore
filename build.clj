(ns build
  (:refer-clojure :exclude [test])
  (:require [clojure.tools.build.api :as b]))

(def lib 'voxmachina/itstore)
(def major 0)
(def minor 2)
(def version (format "%d.%d.%s" major minor (b/git-count-revs nil)))
(def version-file "version.edn")
(def class-dir "target/classes")

(defn test "Run all the tests." [opts]
  (let [basis    (b/create-basis {:aliases [:test]})
        cmds     (b/java-command
                  {:basis      basis
                    :main      'clojure.main
                    :main-args ["-m" "cognitect.test-runner"]})
        {:keys [exit]} (b/process cmds)]
    (when-not (zero? exit) (throw (ex-info "Tests failed" {}))))
  opts)

(defn- pom-template [version]
  [[:description "Indieweb post repository with multiple implementations."]
   [:url "https://github.com/vox-machina/itstore"]
   [:licenses
    [:license
     [:name "MIT License"]
     [:url "https://opensource.org/license/mit/"]]]
   [:developers
    [:developer
     [:name "rossajmcd"]]]
   [:scm
    [:url "https://github.com/vox-machina/itstore"]
    [:connection "scm:git:https://github.com/vox-machina/itstore.git"]
    [:developerConnection "scm:git:ssh:git@github.com:vox-machina/itstore.git"]
    [:tag (str "v" version)]]])

(defn- jar-opts [opts]
  (assoc opts
         :lib lib   :version version
         :jar-file  (format "target/%s-%s.jar" lib version)
         :basis     (b/create-basis {})
         :class-dir class-dir
         :target    "target"
         :src-dirs  ["src"]
         :pom-data  (pom-template version)))


(defn ci "Run the CI pipeline of tests (and build the JAR)." [opts]
  (println "Running continuous integration...")
  (println "version: " version)
  (test opts)
  (b/delete {:path "target"})
  (let [opts (jar-opts opts)]
    (println "\nWriting pom.xml...")
    (b/write-pom opts)
    (println "\nCopying source...")
    (b/copy-dir {:src-dirs ["resources" "src"] :target-dir class-dir})
    (println "\nBuilding JAR..." (:jar-file opts))
    (b/jar opts))
  opts)

(defn install "Install the JAR locally." [opts]
  (let [opts (jar-opts opts)]
    (b/install opts))
  opts)
