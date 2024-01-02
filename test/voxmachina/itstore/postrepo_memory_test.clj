(ns voxmachina.itstore.postrepo-memory-test
  (:require [clojure.test :refer [deftest is testing]]
            [voxmachina.itstore.core :as core]
            [voxmachina.itstore.postrepo :as its]
            [voxmachina.itstore.postrepo-memory :as itsmem]))

(def pr-mem (itsmem/repo (atom {})))

(defn create-post [id data] (its/create pr-mem id data))

(defn get-post [id] (its/fetch pr-mem id))

(deftest create-post-test
  (testing "Receive correct representation on post creation"
    (is (= {"1" {:name "CreateTest"}} (create-post "1" {:name "CreateTest"})))))

(deftest fetch-post-test
  (testing "Retrieve a post from post repo"
    (is (= {:name "CreateTest"} (get-post "1")))))

