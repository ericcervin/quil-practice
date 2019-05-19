(ns quil-practice.quilpractice20190513
  (:require [quil.core :as q]
            [quil.middleware :as m]))

;;quil rewrite of https://github.com/dantheobserver/drawing-experiments/blob/master/src/experiments/07_dedication.clj
        
(defn cur-time [] (System/currentTimeMillis))

(defn setup []
  (q/frame-rate 8)
  {:env {:orb-mult 2
         :len-mult 16}})
         
(defn next-rad
  [time n env]
  (-> (/ time 4)
      (+ (/ n 128))
      (q/cos)
      (* (env :orb-mult))))

(defn next-pos [x0 y0 time n env]
  (let [val (- (rand-int 10) (/ time n))
        x1 (-> (q/cos val) (* (env :len-mult)) (+ x0))
        y1 (-> (q/sin val) (* (env :len-mult)) (+ y0))]
    [x1 y1]))


(defn frame-state  
  [time frame-num x-start y-start env]
  (let [rad (next-rad time frame-num env)
        end (next-pos x-start y-start time frame-num env)]
    ;;(println rad)
    {:next-origin end
     :line {:start [x-start y-start]
            :end end}
            
     :circle {:x x-start
              :y y-start
              :radius rad}}))
                      

(defn draw-orb! [{:keys [x y radius]}]
  (q/stroke 255)
  (q/ellipse x y radius radius))


(defn draw-line! [{:keys [start end]}]
     (q/stroke 255)
     (q/line (start 0) (start 1) (end 0) (end 1)))

(defn update-state [state]
   state) 

(defn draw [state]
  (q/background 131 118 156)
  (let [frames 32
        x0 150
        y0 150
        start-time (cur-time)]
       (dotimes [_ 8]          
         (loop [n frames                 
                [x y] [x0 y0]]            
           (if (> n 0)
             (do
               (let [time-diff (- (cur-time) start-time)     
                     {:keys [line circle next-origin]} (frame-state time-diff n x y (:env state))]             
                 ;;(println n)
                 (draw-orb! circle)
                 (draw-line! line)
                 (recur (dec n) next-origin))))))))
  
         

(q/defsketch first-try
  :size [300 300]
  :setup setup
  :draw draw
  :update update-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])
