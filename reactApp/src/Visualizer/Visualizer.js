import React, { useRef, useEffect } from "react";
import style from './visualizer.css'
import Layout from "../Header/Layout";

const Visualizer = () => {
    const canvasRef = useRef(null);

    useEffect(() => {
        const canvas = canvasRef.current;
        if (!canvas) {
            return; // Canvas element not yet available
        }
        const ctx = canvas.getContext('2d');
        canvas.width = window.innerWidth;
        canvas.height = window.innerHeight;
    
        class Bar {
            constructor() {
    
            }
            update() {
     
            }
            draw() {
    
            }
        }
    
        const animate = () => {
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            //generate audio samples from microphone
            //animate bars based on microphone data
            console.log('animate');
            requestAnimationFrame(animate);
        }
        animate();
    }, []);


    return (
        <Layout>
            <div className={style.main}>
                <div className={style.body}>
                    <canvas className={style.canvas}>

                    </canvas>

                </div>


            </div>
        </Layout>

    )
}

export default Visualizer;