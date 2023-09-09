import React, { useRef, useEffect, useState } from "react"

import style from './visualizer.css';
import win from '../Assets/sounds/Win sound.wav';

const Visualizer = () => {

    const [audioSource, setAudioSource] = useState(null);

    const handleAudioFileUpload = (event) => {
        const file = event.target.files[0];
        if(file) {
            const audioUrl = URL.createObjectURL(file);
            setAudioSource(audioUrl);
        }
    }

    let audio1 = new Audio();
    audio1.src = audioSource;
    const audioCtx = new AudioContext();

    const canvasRef = useRef(null);
    const audio1Ref = useRef(null);

    let audioSourceForContext;
    let analyser;

    const playLoadedSound = () => {
        if(audio1.src) {
            audio1.play();
            audioSourceForContext = audioCtx.createMediaElementSource(audio1);
            analyser = audioCtx.createAnalyser();
            audioSourceForContext.connect(analyser);
            analyser.connect(audioCtx.destination);
        }
    }

    useEffect(() => {

        // if(audioSource && audio1Ref.current) {
        //     audio1Ref.current.src = audioSource;
        // }

        if(audioSource) {
            audio1.src = audioSource;
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