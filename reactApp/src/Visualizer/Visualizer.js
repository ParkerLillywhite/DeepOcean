import React, { useRef, useEffect, useState } from "react";
import style from './visualizer.css'
import Layout from "../Header/Layout";

class Bar {
    constructor(x, y, width, height, color, index) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.index = index;
    }

    update(micInput) {
        this.height = micInput * 1000;
    }
    updateSlowRecover(micInput) {
        const sound = micInput * 1000;
        if(sound > this.height) {
            this.height = sound;
        } else {
            this.height -= this.height * 0.03;
        }
    }
    drawBasic(context) {
        context.fillStyle = this.color;
        context.fillRect(this.x, this.y, this.width, this.height);
    }
    drawShape2(context) {
        context.strokeStyle = this.color;
        context.save();

        context.translate(context.canvas.width/2, context.canvas.height/2);
        context.rotate(this.index * 0.03);
        context.beginPath();
        context.moveTo(0, 0);
        context.lineTo(0, this.height);
        context.stroke();

        context.restore();
    }
    drawSpiral(context, volume) {
        context.strokeStyle = this.color;
        context.save();

        context.translate(0, 0);
        context.rotate(this.index * 0.03);
        context.scale(1 + volume * 0.2, 1 + volume * 0.2);

        context.beginPath();
        // context.moveTo(this.x, this.y);
        // context.lineTo(this.y, this.height);
        context.bezierCurveTo(100, 100, this.height, this.height, this.x, this.y * 2);
        context.stroke();
        context.strokeRect(this.x, this.y, this.width, this.height);

        //for circles
        // context.arc(this.x + this.index * 2.5, this.y, this.height * 0.5, 0, Math.PI * 2)
        //context.stroke

        context.restore();
    }
}

class Microphone {
    constructor(fftSize) {
        this.initialized = false;
        navigator.mediaDevices.getUserMedia({audio:true})
        .then((stream) => {
            this.audioContext = new AudioContext();
            this.microphone = this.audioContext.createMediaStreamSource(stream)
            this.analyser = this.audioContext.createAnalyser();
            this.analyser.fftSize = fftSize;
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
    const [visualizationMode, setVisualizationMode] = useState("bar");

    useEffect(() => {
        const canvas = canvasRef.current;
        if (!canvas) {
            return; // Canvas element not yet available
        }
        const ctx = canvas.getContext('2d');
        canvas.width = window.innerWidth;
        canvas.height = window.innerHeight;
        
        const fftSize = 1024;
        const microphone = new Microphone(fftSize);

        let bars = [];
        let barWidth = canvas.width/(fftSize/2);
        const createBars = () => {
            switch(visualizationMode) {
                case "bar":
                case "shape2":
                    for(let i = 0; i < (fftSize/2); i++) {
                        let color = 'hsl(' + i + ', 100%, 50%)'
                        bars.push(new Bar(i * barWidth, canvas.height/2, 1, 20, color, i));
                    }
                    break;
                case "spiral":
                    for(let i = 0; i < (fftSize/2); i++) {
                        let color = 'hsl(' + i + ', 100%, 50%)'
                        bars.push(new Bar(0, i, 1, 50, color, i));
                    }
                    break;
                
            }

        }
        createBars();
        let angle = 0;
    
        const animate = () => {
            if(microphone.initialized) {
                ctx.clearRect(0, 0, canvas.width, canvas.height);
                //generate audio samples from microphone
                const samples = microphone.getSamples();
                const volume = microphone.getVolume();
                //animate bars based on microphone data
                switch(visualizationMode) {
                    case "bar":
                        bars.forEach((bar, i) => {
                            bar.update(samples[i]);
                            bar.drawBasic(ctx);
                        });
                    break;
                    case "shape2":
                        bars.forEach((bar, i) => {
                            bar.updateSlowRecover(samples[i]);
                            bar.drawShape2(ctx);
                        });
                    break;
                    case "spiral":
                        angle -= 0.03;
                        ctx.save();
                        ctx.translate(canvas.width/2, canvas.height/2);
                        ctx.rotate(angle);
                        bars.forEach((bar, i) => {
                            bar.updateSlowRecover(samples[i]);
                            bar.drawSpiral(ctx, volume);
                        });
                        ctx.restore();
                    break;
                }
                
            }
            requestAnimationFrame(animate);
        }
        animate();
    }, [visualizationMode]);

    const handleVisualizationMode = () => {
        switch(visualizationMode) {
            case "bar":
                setVisualizationMode("shape2");
                break;
            case "shape2":
                setVisualizationMode("spiral");
                break;
            case "spiral":
                setVisualizationMode("bar");
                break;
        }
    }

    return (
        <Layout>
            <div>
                <button onClick={() => handleVisualizationMode()}>
                    Change Shape
                </button>
                <p>
                    {visualizationMode}
                </p>
            </div>
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