(ns quil-practice.quilpractice20190510
  (:require [quil.core :as q]
            [quil.middleware :as m]))

;;quil rewrite of https://github.com/dantheobserver/drawing-experiments/blob/master/src/experiments/02_cloud_trail.clj

(defn make-droplet [spawn-pos x-range y-range]
  (let [x (+ (- (:x spawn-pos) x-range) (rand-int (* 2 x-range)))
        y (+ (- (:y spawn-pos) y-range) (rand-int (* 2 y-range)))
        size (+ (rand-int 90) 10)
        rate (+ (rand) 1)
        color [255 (+ (rand-int 100) 100)]]
    {:x x :y y :size size :rate rate :color color}))

(defn move-droplet [spawn-pos x-range y-range droplet]
  (if (> (:size droplet) 0)
    (-> droplet 
        (update :y + 10)
        (update :size - 2))
    (make-droplet spawn-pos x-range y-range)))
    
(defn setup []
  (let [spawn-pos {:x 250 :y 250}
        x-range 60
        y-range 60]        
   {:spawn-pos spawn-pos
    :x-range x-range
    :y-range y-range
    :droplets (repeatedly 100 #(make-droplet spawn-pos x-range y-range))}))        
        

(defn update-state [state]
   (let [{:keys [spawn-pos x-range y-range droplets]} state
         dps (map #(move-droplet spawn-pos x-range y-range %) droplets)]
       
    (assoc state :droplets dps)))  

(defn draw [state]
  (q/background 84 116 211)
  (doseq [droplet (:droplets state)]
    (let [{:keys [x y size rate color]} droplet
          [c a] color]
          
      (q/fill c a)
      (if (> (q/frame-count) 90)
        (q/ellipse x y size size)))))

      

(q/defsketch cloud-trail
  :size [500 500]
  :setup setup
  :draw draw
  :update update-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])
