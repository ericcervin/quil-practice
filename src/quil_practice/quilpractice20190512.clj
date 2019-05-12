(ns quil-practice.quilpractice20190512
  (:require [quil.core :as q]
            [quil.middleware :as m]))

;;quil rewrite of https://github.com/dantheobserver/drawing-experiments/blob/master/src/experiments/03_deep_rain.clj

(defrecord RainDrop [x y z speed accel])

(defn new-drop [max-x max-y max-z]
  (->RainDrop
    (rand-int max-x) 
    max-y 
    (rand-int max-z)
    0.0
    (+ (rand 1.7) 0.3)))
   
(defn render-drop [{:keys [x y z] :as drop} max-depth]
   (let [alpha (* (/ z max-depth) 255)]
     (q/stroke 255 255 255 alpha)
     (q/fill 255 255 255 alpha)
     (q/ellipse x y 2 10)))

(defn update-drop [max-depth drop]
   (let [{:keys [x y z speed accel]} drop]
      (if (< y (q/height))
        (-> drop
            (update :y + speed)
            (update :speed + accel))
        (new-drop (q/width) -200 max-depth))))
        


(defn setup []
  (q/frame-rate 30)
  (let [max-depth 30]
   {:wind 0
    :max-drops 100
    :max-depth max-depth
    :droplets (repeatedly 300 #(new-drop (q/width) -200 max-depth))}))        
        

(defn update-state [state]
   (let [{:keys [max-depth droplets]} state
         dps (map #(update-drop max-depth %) droplets)]
       
    (assoc state :droplets dps))) 

(defn draw [state]
  (q/background 8 6 28)
  (let [max-depth (:max-depth state)]
   (doseq [droplet (:droplets state)]
     (render-drop droplet max-depth))))
         

(q/defsketch rain-scene
  :size [800 600]
  :setup setup
  :draw draw
  :update update-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])
