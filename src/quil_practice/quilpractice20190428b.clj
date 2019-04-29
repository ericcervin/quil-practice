(ns quil-practice.quilpractice20190428b
  (:require [quil.core :as q]
            [quil.middleware :as m]))

;;quil rewrite of https://github.com/dantheobserver/drawing-experiments/blob/master/src/experiments/01_confetti.clj

(defn rcolor [] (rand-int 255))
(defn rrate []  (+ (rand-int 20) 10))


(defn setup []  
  {:droplets (take 1000 (repeatedly (fn []
                                     (let [size (+ (rand-int 10) 10)]
                                       {:size [size size]
                                        :color [(rcolor) (rcolor) (rcolor) (+ (rand-int 120) 135)]
                                        :rate (rrate)
                                        :pos [(rand-int (q/width)) (rand-int (q/height))]}))))})

(defn move-droplet [droplet]
  (let [{:keys [pos rate]} droplet
        [_ y] pos]

    (if (< y (q/height))
      (update-in droplet [:pos 1] #(+ rate %))
      (-> droplet
          (assoc :rate (rrate))
          (assoc-in [:pos 0] (rand-int (q/width)))
          (assoc-in [:pos 1] 0)))))

(defn update-state [state]
  (update state :droplets #(map move-droplet %)))
  

(defn draw [state]
  (q/background 0)
  (doseq [droplet (:droplets state)]
    (let [{:keys [size color pos]} droplet
          [w h] size
          [r g b a] color
          [x y] pos]
      (q/fill r g b a)
      (q/ellipse x y w h))))

      

(q/defsketch confetti
  :size [800 600]
  :setup setup
  :draw draw
  :update update-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])
