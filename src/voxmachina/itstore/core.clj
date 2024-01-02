(ns voxmachina.itstore.core
  "Demonstrates use of the library outside the context of unit tests."
  (:require [voxmachina.itstore.postrepo :as its]
            [voxmachina.itstore.postrepo-memory :as itsmem]))

(def post-repo (itsmem/repo (atom {})))

(defn create-post [id data] (its/create post-repo id data))

(defn post [id] (its/fetch post-repo id))

