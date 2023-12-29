(ns voxmachina.itstore.postrepo)

(defprotocol PostRepo
  "Operations PostRepo implementations are not necessarily atomic (e.g. with FS Repository)."
  (create [this id data] "Creates a new post returning a map representing it.")
  (fetch  [this id]      "Fetches a post returning a map representing it.")
  (delete [this id]      "Deletes a post with given id and returns a nil."))

