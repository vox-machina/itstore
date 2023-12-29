(ns voxmachina.itstore.postrepo-memory
  (:require [voxmachina.itstore.postrepo :as its]))

(defn create [state id data] (swap! state assoc id data))

(defn fetch [state id] (get @state id))

(defn delete [state id] (swap! state dissoc id))

(deftype PostRepoMemory [state]
  its/PostRepo
  (create [_this id data] (create state id data))
  (fetch  [_this id]      (fetch state id))
  (delete [_this id]      (delete state id)))

(defn repo [state] (PostRepoMemory. state))


