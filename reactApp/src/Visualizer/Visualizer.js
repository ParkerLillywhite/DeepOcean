import React, { useRef, useEffect, useState } from "react"

import style from './visualizer.css';
import win from '../Assets/sounds/Win sound.wav';

const Visualizer = () => {

    const [audioSource, setAudioSource] = useState(null);

    const handleBase64Conversion = (audioFile, callback) => {
        const reader =  new FileReader();
        reader.onload = (event) => {
            const base64String = event.target.result.split(',')[1];
            callback(base64String);
        }
        reader.readAsDataURL(audioFile)
    }

    const handleAudioFileUpload = (event) => {
        const file = event.target.files[0];
        if(file) {
            handleBase64Conversion(file, (base64String) => {
                setAudioSource(base64String);
            });
        }
    }

    let audio1 = new Audio();
    audio1.src = audioSource;
    const audioCtx = new AudioContext();

    const canvasRef = useRef(null);
    const audio1Ref = useRef(null);

    const playLoadedSound = () => {
        audio1.play();
    }

    useEffect(() => {

        if(audioSource) {
            audio1Ref.current.src = audioSource;
        }

        const canvas = canvasRef.current;
        if(!canvas) {
            return; //Canvas element is not yet available
        }
        canvas.width = window.innerWidth;
        canvas.height = window.innerHeight;
        const ctx = canvas.getContext('2d');
    }, [audioSource]);

    const playTestSound = () => {
        const oscillator = audioCtx.createOscillator();
        oscillator.connect(audioCtx.destination);
        oscillator.type = 'triangle';
        oscillator.start();
        setTimeout(() => {
            oscillator.stop();
        }, 1000)
    }
    return(
        <div className={style.main}>
            <button onClick={() => playTestSound()}>
                Test Visualizer
            </button>
            
            <div className={style.fileUpload}>
                <input type="file" accept="audio/*" onChange={handleAudioFileUpload} />
                {/* {audioSource && <audio controls src={`data:audio/wav;base64,${audioSource}`} />} */}
            </div>

            <button onClick={() => playLoadedSound()}>
                Play Sound
            </button>

            <div className={style.body}>
                <canvas ref={canvasRef} className={style.canvas}></canvas>
                <audio className={style.audio1} controls></audio>
            </div>
        </div>
    )
}

export default Visualizer;