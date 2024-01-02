(ns voxmachina.itstore.postrepo-fs
  (:require [clojure.pprint :as pp]
            [voxmachina.itstore.postrepo :as its])
  (:import java.io.File))

(defn- pretty-spit
  [f-name xs]
  (spit (File. f-name) (with-out-str (pp/write xs :dispatch pp/code-dispatch))))

(defn create [{:keys [data-path]} path data]
  (pretty-spit (str data-path "/" path) data)
  data)

(deftype PostRepoFS [cfg]
  its/PostRepo
  (create [_this path data] (create cfg path data)))

(defn repo [cfg] (PostRepoFS. cfg))
