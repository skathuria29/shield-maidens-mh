# SENSUM | Mental Wellbeing

Sensum is a platform that helps to improve mental wellbeing by leveraging technology to diagnose problems through group interaction based solutions. An app that will help normalize the feelings, helping people feel comfortable in sharing their thoughts, state and mental health issues.

## Contents

1. [Overview](#overview)
1. [Youtube Link](#youtube-link)
1. [The idea](#the-idea)
1. [The architecture](#the-architecture)
1. [Prerequisite](#prerequisite)
1. [Technology](#technology)
1. [Setup](#setup)
1. [Documents](#documents)

## Overview

### What's the problem?
There is a mismatch between the supply and demand in the mental health space with **4000 mental health professionals for ~100 Mn people who suffer from mental health problems** in India as per a WHO report. Mental wellbeing is being further impacted due to the ongoing pandemic and lockdown aggravating the gap between demand and supply.

### How can technology help?
Due to the ongoing pandemic and stringent social distancing protocol, we are providing an Android App solution for **Mental Wellness** by
- Digitalizing the self assessment and individual/group video call sessions. 
- With the current solution we are normalising the results of Q&A, Voice and Video input.
- Using the capabilities of ML for analysing emotions by using **IBM Watson Speech to Text and Tone Analyzer** and **Face recognition** through native android library.
- Planning to leverage the **Face Recognition** ML technology to help healthcare practitioners monitor the sessions and get generated reports of individual participant's progress and to provide appropriate treatment.


## Youtube link
https://www.youtube.com/watch?v=4KVQSuz5Yes


## The idea
**Target group**
Digital natives in Tier 1 cities who suffer from mental health problems 

**Business Model**
Product will have a freemium model where users will be provided initial live group therapy sessions for free. They will be charged for these sessions after the free sessions. Pricing models will be of two kinds : Price per session, Subscription price.


## The architecture

### Sensum App 
![Sensum App](https://github.com/skathuria29/shield-maidens-mh/blob/master/Screenshot%202020-06-06%20at%202.22.32%20PM.png)

### Android app
<img src="https://github.com/skathuria29/shield-maidens-mh/blob/master/Screenshot_20200606-180414.png" width=250> <img src="https://github.com/skathuria29/shield-maidens-mh/blob/master/Screenshot_20200606-175331.png" width=250> <img src="https://github.com/skathuria29/shield-maidens-mh/blob/master/Screenshot_20200606-175543.png" width=250>

<img src="https://github.com/skathuria29/shield-maidens-mh/blob/master/videogif.gif" width=250>

A user journey in app is going through following steps:-
1. User is asked to enter name for a record and is asked to take a wellbeing assessment test to track the current state of well being.
2. Assessment contains multiple choice questions and some questions to answer with voice/video input.
3. User can either give a audio input or a video recording, recorded audio input can be listened using a player and can be recorded again. For Video input user visits a new screen in which app detects the facial expressions and emotions and displays the metric on screen like sadnesss, joy, angry, disgust etc.
4. The idea to capture the user response through three medium(Q&A, voice and video) is to determine and analyze the emotions user is feeling with more accurate results. By capturing voice, we use **IBM Watson Speech-to-Text service** to convert the audio into text and the we apply **IBM Watson Tone Analyzer service** which provides the tone of text. For instance sadness, analytical, confidence, joy etc. We average out these three different responses and present user a score which lies between "More critical" and "Less critical" range of anxious meter.
5. Similarly, while recording video we take average of emotions metric captured and display the score.
6. After collating the output of Q&A, video and audio input, user is shown the list of recommended group therapy sessions. These sessions have a certain number of participants and a psychiatrist just like in a round table psychiatrist session. Where user can enter the session and express their feelings and thoughts.
7. We are also recommending user to go through the suggested tasks and activities for more engaging and habit building experience.


## Prerequisite
- Register for an [IBM Cloud](https://www.ibm.com/account/reg/us-en/signup?formid=urx-42793&eventid=cfc-2020?cm_mmc=OSocial_Blog-_-Audience+Developer_Developer+Conversation-_-WW_WW-_-cfc-2020-ghub-starterkit-education_ov75914&cm_mmca1=000039JL&cm_mmca2=10008917) account. 
- Require Node installed [Node](https://nodejs.org/en/download/)
- Android : ndk configuration and system permissions like record Audio, external storage and Camera.

## Technology
- Frontend : Android App
- Backend : NodeJs and mongoDb
- IBM Technology
  - [Watson Speech to Text](https://www.ibm.com/cloud/watson-speech-to-text)
  - [Watson Tone Analyzer](https://www.ibm.com/watson/services/tone-analyzer/)

## Setup
- To run android app, clone the repo and source code is available inside folder **mobileapp/SheildMaidens** 
- To run app on your phones, download and install the apk on your device by giving permission to install from external source.

## Live Demo
Link to working app https://github.com/skathuria29/shield-maidens-mh/releases/tag/1.0
### To get server up and runnig
- Go to the root folder
- run command `npm install`
- run the script `npm run start`



## Documents
Slides:
https://docs.google.com/presentation/d/1-KPyi43BTDX0XVoWsEICykRK6KX2ropQcdcoK27ZIzQ/edit#

Youtube video :
https://www.youtube.com/watch?v=4KVQSuz5Yes&amp;feature=youtu.be
