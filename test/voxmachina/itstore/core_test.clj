(ns voxmachina.itstore.core-test
  (:require [clojure.test :refer [deftest is testing]]
            [voxmachina.itstore.core :as core]
            [voxmachina.itstore.postrepo-memory :as itsmem]))

(deftest create-post
  (testing "Receive correct representatoin upon post creation"
    (with-redefs [voxmachina.itstore.core/post-repo
                  (itsmem/repo (atom {}))]
      (is (= {"1" {:name "PostRepoMemoryCreated"}} (core/create-post "1" {:name "PostRepoMemoryCreated"}))))))

(deftest fetch-post
  (testing "Receive correct representation upon post creation"
    (with-redefs [voxmachina.itstore.core/post-repo
                  (itsmem/repo (atom {"1" {:name "PostRepoMemory"}}))]
      (is (= {:name "PostRepoMemory"} (core/post "1"))))))
