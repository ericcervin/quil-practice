(ns quil-practice.quilpractice20190130
  (:require [quil.core :as q]
            [quil.middleware :as m]))


(defn lttr-file-name [l] (str "/resources/letters/cp6/" l ".png"))
(defn assoc-lttr-img [mp lt] (assoc mp (keyword (str lt)) (q/load-image (lttr-file-name lt))))


(defn setup []
  {:lttr (reduce assoc-lttr-img {} "abcdefghijklmnopqrstuvwxyz")
   :skull {:s2lt (q/load-image "/resources/skulls/cp_6_skull_2_t.png")
           :s2lb (q/load-image "/resources/skulls/cp_6_skull_2_b.png")
           :s2rt (q/load-image "/resources/skulls/cp_6_skull_2_t_xflip.png")
           :s2rb (q/load-image "/resources/skulls/cp_6_skull_2_b_xflip.png")
           :s3t  (q/load-image "/resources/skulls/cp_6_skull_3_t.png")}        
   :setting [
                         
             
             {:img [:lttr :g] :x 200 :y 75 :h 100 :w 100} ;;;;gelukwensen
             {:img [:lttr :e] :x 275 :y 75 :h 50 :w 50}
             {:img [:lttr :l] :x 320 :y 75 :h 50 :w 50}
             {:img [:lttr :u] :x 355 :y 70 :h 50 :w 50}
             {:img [:lttr :k] :x 400 :y 75 :h 50 :w 50}
             {:img [:lttr :w] :x 445 :y 75 :h 100 :w 100}
             {:img [:lttr :e] :x 525 :y 75 :h 100 :w 100}
             {:img [:lttr :n] :x 600 :y 75 :h 50 :w 50}
             {:img [:lttr :s] :x 635 :y 75 :h 50 :w 50}
             {:img [:lttr :e] :x 670 :y 75 :h 50 :w 50}
             {:img [:lttr :n] :x 715 :y 75 :h 100 :w 100}
             
             
             {:img [:skull :s3t] :x -150 :y 150 :h 400 :w 400}
             {:img [:skull :s2lt] :x 20 :y 20 :h 200 :w 200}
             {:img [:skull :s2lb] :x 45 :y 170 :h 150 :w 100}
            
             ]})
             
  

(defn draw [state]
    (q/background 0)
    
    (doseq [i (:setting state)]
      (q/image (get-in state  (:img i)) (:x i) (:y i) (:h i) (:w i))))
       


(q/defsketch practice
  :size [816 333]
  :setup setup
  :draw draw
  :features [:keep-on-top]
  :middleware [m/fun-mode])
  
