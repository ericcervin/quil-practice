(ns quil-practice.quilpractice20190123
  (:require [quil.core :as q]
            [quil.middleware :as m]))


(defn lttr-file-name [l] (str "/resources/letters/cp6/" l ".png"))
(defn assoc-lttr-img [mp lt] (assoc mp (keyword (str lt)) (q/load-image (lttr-file-name lt))))


(defn setup []
  {:lttr (reduce assoc-lttr-img {} "abcdefghijklmnopqrstuvwxyz")
   :setting [{:img [:lttr :m] :x 0 :y 0}
             {:img [:lttr :o] :x 45 :y 0}
             {:img [:lttr :r] :x 90 :y 0}
             {:img [:lttr :e] :x 135 :y 0}
             
             {:img [:lttr :f] :x 0 :y 45}
             {:img [:lttr :u] :x 45 :y 45}
             {:img [:lttr :c] :x 90 :y 45}
             {:img [:lttr :k] :x 135 :y 50}
             {:img [:lttr :e] :x 170 :y 45}
             {:img [:lttr :r] :x 205 :y 45}
             {:img [:lttr :y] :x 240 :y 45}
             
             {:img [:lttr :c] :x 0 :y 95}
             {:img [:lttr :o] :x 45 :y 90}
             {:img [:lttr :m] :x 90 :y 95}
             {:img [:lttr :i] :x 135 :y 95}
             {:img [:lttr :n] :x 170 :y 95}
             {:img [:lttr :g] :x 205 :y 90}
             
             {:img [:lttr :s] :x 0 :y 140}
             {:img [:lttr :o] :x 45 :y 140}
             {:img [:lttr :o] :x 90 :y 140}
             {:img [:lttr :n] :x 135 :y 140}
             {:img [:lttr :i] :x 170 :y 140}
             {:img [:lttr :s] :x 215 :y 140}
             {:img [:lttr :h] :x 260 :y 140}]})
             
             
             
             
             
   


(defn draw [state]
    (q/background 0)
    
    (doseq [i (:setting state)]
      (q/image (get-in state  (:img i)) (:x i) (:y i) 50 50)))
      

    
  
  
   
    
(q/defsketch practice
  :size [960 480]
  :setup setup
  :draw draw
  :features [:keep-on-top]
  :middleware [m/fun-mode])
  
