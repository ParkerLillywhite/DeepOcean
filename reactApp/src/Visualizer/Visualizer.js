import React, { useRef, useEffect } from "react";
import style from './visualizer.css'
import Layout from "../Header/Layout";

class Bar {
    constructor(x, y, width, height, color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    update(micInput) {
        this.height = micInput * 1000;
    }
    draw(context) {
        context.fillStyle = this.color;
        context.fillRect(this.x, this.y, this.width, this.height);
    }
}

class Microphone {
    constructor() {
        this.initialized = false;
        navigator.mediaDevices.getUserMedia({audio:true})
        .then((stream) => {
            this.audioContext = new AudioContext();
            this.microphone = this.audioContext.createMediaStreamSource(stream)
            this.analyser = this.audioContext.createAnalyser();
            this.analyser.fftSize = 512;
            const bufferLength = this.analyser.frequencyBinCount;
            this.dataArray = new Uint8Array(bufferLength);
            this.microphone.connect(this.analyser)
            this.initialized = true;
        }).catch(function(err){
            alert(err);
        });
    }
    getSamples() {
        this.analyser.getByteTimeDomainData(this.dataArray);
        let normSamples = [...this.dataArray].map(e => e/128 - 1);
        return normSamples;
    }
    getVolume() {
        this.analyser.getByteTimeDomainData(this.dataArray);
        let normSamples = [...this.dataArray].map(e => e/128 - 1);
        let sum = 0;
        for(let i = 0; i < normSamples.length; i++) {
            sum += normSamples[i] * normSamples[i];
        }
        let volume = Math.sqrt(sum / normSamples.length);
        return volume;
    }
}

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
    
        const microphone = new Microphone();

        let bars = [];
        let barWidth = canvas.width/256;
        const createBars = () => {
            for(let i = 0; i < 256; i++) {
                let color = 'hsl(' + i + ', 100%, 50%)'
                bars.push(new Bar(i * barWidth, canvas.height/2, 1, 20, color));
            }
        }
        createBars();
    
        const animate = () => {
            if(microphone.initialized) {
                ctx.clearRect(0, 0, canvas.width, canvas.height);
                //generate audio samples from microphone
                const samples = microphone.getSamples();
                //animate bars based on microphone data
                bars.forEach((bar, i) => {
                    bar.update(samples[i]);
                    bar.draw(ctx);
                });
            }
            requestAnimationFrame(animate);
        }
        animate();
    }, []);


    return (
        <Layout>
            <div className={style.main}>
                <div className={style.body}>
                    <canvas ref={canvasRef} className={style.canvas}>
                        
                    </canvas>
                </div>
            </div>
        </Layout>
    )
}

export default Visualizer;