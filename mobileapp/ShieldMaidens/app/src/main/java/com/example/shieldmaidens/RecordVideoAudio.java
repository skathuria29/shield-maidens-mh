package com.example.shieldmaidens;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shieldmaidens.TonePojo.Tones;
import com.example.shieldmaidens.facerecognition.affdexme.MainActivity1;
import com.gauravk.audiovisualizer.visualizer.BarVisualizer;
import com.visualizer.amplitude.AudioRecordView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import cafe.adriel.androidaudioconverter.AndroidAudioConverter;
import cafe.adriel.androidaudioconverter.callback.IConvertCallback;
import cafe.adriel.androidaudioconverter.model.AudioFormat;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RecordVideoAudio extends AppCompatActivity {

    private ConstraintLayout audio;
    private ConstraintLayout video;
    private String fileName;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static final int REQUEST_RECORD_CAMERA_PERMISSION = 300;

    private MediaRecorder recorder = null;
    private MediaPlayer player = null;
    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA};
    private boolean startRecording = true;
    private BarVisualizer bar;
    private TextView record_audio;
    private AudioRecordView audioRecordView;
    Timer timer;
    private ImageView playIcon;
    private boolean mStartPlaying = true;
    private ConstraintLayout cl_play;
    private File flacFile;
    private ImageView ic_audio;
    private ImageView cross;
    private TextView record_video;
    private Double percentage;
    private ImageView btn_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_video_audio);

        fileName = getExternalCacheDir().getAbsolutePath();
        fileName += "/audiorecordtest.3gp";

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);


        audio = findViewById(R.id.cl_audio);
        record_audio = findViewById(R.id.record_audio);
        video = findViewById(R.id.cl_video);
        bar = findViewById(R.id.bars);
        audioRecordView = findViewById(R.id.audioRecordView);
        playIcon = findViewById(R.id.playIcon);
        cl_play = findViewById(R.id.cl_play);
        ic_audio = findViewById(R.id.ic_audio);
        cross = findViewById(R.id.cross);
        record_video = findViewById(R.id.record_video);
        btn_continue = findViewById(R.id.btn_continue);

        record_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecord(startRecording);
                if (startRecording) {
                    record_audio.setText("Recording...");
                    ic_audio.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_pause_dark));
                } else {
                    record_audio.setText("Answer with voice ");
                    ic_audio.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.audio));

                }
                startRecording = !startRecording;
            }
        });

        playIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlay(mStartPlaying);
                if (mStartPlaying) {

                } else {
                    // bar.hide();
                }
                mStartPlaying = !mStartPlaying;
            }
        });

        record_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecordVideoAudio.this, MainActivity1.class);
                startActivity(intent);
            }
        });

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
            }
        });

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecordVideoAudio.this, AssesmentResultScreen.class);
                intent.putExtra("percentage", percentage);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted = (grantResults[0] == PackageManager.PERMISSION_GRANTED) && (grantResults[1] == PackageManager.PERMISSION_GRANTED);
                break;
        }
        if (!permissionToRecordAccepted) finish();

    }

    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            pausePlaying();
        }
    }

    private void startPlaying() {
        playIcon.setVisibility(View.VISIBLE);
        bar.setVisibility(View.VISIBLE);
        player = new MediaPlayer();
        try {
            player.setDataSource(fileName);
            player.prepare();
            player.start();

            int audioSessionId = player.getAudioSessionId();
            if (audioSessionId != -1)
                bar.setAudioSessionId(audioSessionId);
        } catch (IOException e) {
            //  Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        cl_play.setVisibility(View.GONE);
        player.release();
        player = null;

    }

    private void pausePlaying() {
        player.pause();
    }

    private void startRecording() {
        cl_play.setVisibility(View.VISIBLE);
        bar.setVisibility(View.GONE);
        playIcon.setVisibility(View.GONE);
        cross.setVisibility(View.GONE);
        audioRecordView.setVisibility(View.VISIBLE);

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                int currentMaxAmplitude = recorder.getMaxAmplitude();
                audioRecordView.update(currentMaxAmplitude);
            }
        }, 0, 100);
        try {
            recorder.prepare();
        } catch (IOException e) {
            //Log.e(LOG_TAG, "prepare() failed");
        }

        recorder.start();

    }

    private void stopRecording() {
        bar.setVisibility(View.VISIBLE);
        playIcon.setVisibility(View.VISIBLE);
        cross.setVisibility(View.VISIBLE);
        audioRecordView.setVisibility(View.GONE);
        timer.cancel();
        audioRecordView.recreate();
        recorder.stop();
        recorder.release();
        recorder = null;

        convertFile();

    }

    private void convertFile() {
        IConvertCallback callback = new IConvertCallback() {
            @Override
            public void onSuccess(File convertedFile) {
                // So fast? Love it!
                //flacFile = convertedFile
                fetchTextFromAudioFizle(convertedFile);
            }

            @Override
            public void onFailure(Exception error) {
                // Oops! Something went wrong
                Log.e("Error", error.getMessage());
            }
        };
        AndroidAudioConverter.with(this)
                // Your current audio file
                .setFile(new File(fileName))
                // Your desired audio format
                .setFormat(AudioFormat.FLAC)
                // An callback to know when conversion is finished
                .setCallback(callback)
                // Start conversion
                .convert();
    }

    private void fetchTextFromAudioFizle(File convertedFile) {
        getToneAnalyzerInputs(convertedFile).subscribeWith(new DisposableObserver<Tones>() {
            @Override
            public void onNext(Tones tones) {
                percentage = tones.getDocumentTone().getTones().get(0).getScore();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private Observable<Tones> getToneAnalyzerInputs(File convertedFile) {
        return getspeechToTextResonse(convertedFile).map(new Function<SpeechText, String>() {
            @Override
            public String apply(SpeechText speechText) throws Exception {
                if (!speechText.getResults().isEmpty()) {
                    if (!speechText.getResults().get(0).getAlternatives().isEmpty()) {
                        return speechText.getResults().get(0).getAlternatives().get(0).getTranscript();
                    }
                }
                return "Not feeling Great";
            }
        }).flatMap(new Function<String, ObservableSource<Tones>>() {
            @Override
            public ObservableSource<Tones> apply(String s) throws Exception {
                return RemoteDataSource.getInstance().createApiService2(ToneAnalyzer.class).
                        fetchToneMetric(Credentials.basic("apikey", ApiConstants.TONE_ANALYZER_API_KEY),
                                new SimpleDateFormat("yyyy-MM-dd").format(new Date()), s);
            }
        });
    }

    private Observable<SpeechText> getspeechToTextResonse(File convertedFile) {
        RequestBody fbody = RequestBody.create(MediaType.parse("audio/basic"), convertedFile);
        String basic = Credentials.basic("apikey", ApiConstants.SPEECH_TO_TEXT_API_KEY);
        SpeechToText speechToText = RemoteDataSource.getInstance().createApiService(SpeechToText.class);
        return speechToText.fetchTextFromSpeech(fbody, basic)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    private void callToneAnalyzer(SpeechText speechText) {
        if (!speechText.getResults().isEmpty()) {
            RestClient.getClient2().create(ToneAnalyzer.class).
                    fetchToneMetric(Credentials.basic("apikey", ApiConstants.TONE_ANALYZER_API_KEY),
                            new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                            speechText.getResults().get(0).getAlternatives().get(0).getTranscript()
                    )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Tones>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Tones tones) {
                            percentage = tones.getDocumentTone().getTones().get(0).getScore();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }

        if (player != null) {
            player.release();
            player = null;
        }

        bar.release();
    }
}
