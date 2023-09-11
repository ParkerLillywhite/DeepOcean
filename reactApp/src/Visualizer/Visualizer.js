import React, { useRef, useEffect, useState } from "react"

import style from './visualizer.css';
import win from '../Assets/sounds/Win sound.wav';

const Visualizer = () => {

    const [audioSource, setAudioSource] = useState(null);
    const canvasRef = useRef(null);
    const audio1Ref = useRef(null);
    const analyserRef = useRef(null);
    const audioCtxRef = useRef(null);
    let ctx;

    const handleAudioFileUpload = (event) => {
        const file = event.target.files[0];
        if(file) {
            const audioUrl = URL.createObjectURL(file);
            setAudioSource(audioUrl);
        }
    }

    const createAudioContext = () => {
        const audioCtx = new AudioContext();
        audioCtxRef.current = audioCtx;
        analyserRef.current = audioCtx.createAnalyser();
        analyserRef.current.fftSize = 64;
        analyserRef.current.connect(audioCtx.destination);
    }

    const connectAudioSource = () => {
        const audio1 = audio1Ref.current;
        audio1.src = audioSource;

        const audioCtx = audioCtxRef.current;
        const sourceNode = audioCtx.createMediaElementSource(audio1);
        sourceNode.connect(analyserRef.current);
        
        analyserRef.current.fftSize = 64;
    }

    const playLoadedSound = () => {
        const audio1 = audio1Ref.current;
        audio1.play();

        const bufferLength = analyserRef.current.frequencyBinCount;
        const dataArray = new Uint8Array(bufferLength);

        const animate = () => {
            const barWidth = canvasRef.current.width/bufferLength;
            let x = 0;
            ctx.clearRect(0, 0, canvasRef.current.width, canvasRef.current.height);
            analyserRef.current.getByteFrequencyData(dataArray);
            for (let i  = 0; i < bufferLength; i++) {
                const barHeight = dataArray[i];
                ctx.fillStyle = 'white';
                ctx.fillRect(x, canvasRef.current.height - barHeight, barWidth, barHeight);
                x += barWidth;
            }
            requestAnimationFrame(animate);
        }
        animate();
    }

    useEffect(() => {
        createAudioContext();

        if(audioSource) {
            connectAudioSource();
        }

        const canvas = canvasRef.current;
        if(!canvas) {
            return; //Canvas element is not yet available
        }
        canvas.width = window.innerWidth;
        canvas.height = window.innerHeight;
        ctx = canvas.getContext('2d');

        return () => {
            if(audioCtxRef.current) {
                audioCtxRef.current.close();
            }
        }

    }, [audioSource]);

    return(
        <div className={style.main}>
            
            <div className={style.fileUpload}>
                <input type="file" accept="audio/*" onChange={handleAudioFileUpload} />
                {/* {audioSource && <audio controls src={audioSource} />} */}
            </div>

            <button onClick={() => playLoadedSound()}>
                Play Sound
            </button>

            <div className={style.body}>
                <canvas ref={canvasRef} className={style.canvas}></canvas>
                <audio className={style.audio1} ref={audio1Ref} controls></audio>
            </div>
        </div>
    )
}

export default Visualizer;