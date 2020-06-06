# SENSUM | Mental Wellbeing

Sensum is a platform that helps to improve mental wellbeing by leveraging technology to diagnose the mental health issues using group interaction based solutions. An app that will help normalize the feelings, helping people feel comfortable in sharing their thoughts, state and mental health issues.

## Contents

1. [Overview](#overview)
1. [The idea](#the-idea)
1. [The architecture](#the-architecture)
1. [Prerequisite](#prerequisite)
1. [Technology](#technology)
1. [Setup](#setup)
1. [Documents](#documents)
1. [License](#license)

## Overview

### What's the problem?
There is a mismatch between the supply and demand in the mental health space with **4000 mental health professionals for ~100 Mn people who suffer from mental health problems** in India as per a WHO report. Mental wellbeing is being further impacted due to the ongoing pandemic and lockdown aggravating the gap between demand and supply.

### How can technology help?
Due to the ongoing pandemic and stringent social distancing protocol, we are providing an Android App solution for **Mental Wellness** by
- Digitalizing the self assessment and individual/group video call sessions. 
- With the current solution we are normalising the results of Q&A, Voice and Video input.
- Using the capabilities of ML for analysing emotions by using **IBM Watson Speech to Text and Tone Analyzer** and **Face recognition** through native android library.
- Planning to leverage the **Face Recognition** ML technology to help healthcare practitioners monitor the sessions and get generated reports of individual participant's progress and to provide appropriate treatment.


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
1. User is asked to enter name for a record and is asked to take a wellbeing assessment test to track the current state of mind.
2. Assessment contains few questions which has four options and questions with voice/video input.
3. User can either give a audio input or a video recording, recorded audio input can be listened using a player and can be recorded again. For Video input user visits a new screen in which app detects the facial expressions and emotions and displays the metric on screen like sadnesss, joy, angry, disgust etc.
4. The idea to capture the user response through three medium(Q&A, voice and video) is to determine and analyze the emotions user is feeling with more accurate results. By capturing voice, we use **IBM Watson Speech-to-Text service** to convert the audio into text and the we apply **IBM Watson Tone Analyzer service** which provides the tone of text. For instance sadness, analytical, confidence, joy etc. We average out these three different responses and present user a score which lies between "More critical" and "Less critical" range of anxious meter.
5. Similarly, while recording video we take average of emotions metric captured and display the score.
6. Based on the Q&A, video and audio input we show user list of recommended group therapy sessions  for anxiety . 
4. The idea to capture the user response through three medium(Q&A, voice and video) is to determine and analyze the emotions user is feeling with more accurate results. By capturing voice, we use **IBM Watson Speech-to-Text service** to convert the audio into text and the we apply **IBM Watson Tone Analyzer service** which provides the tone of text. For instance sadness, analytical, confidence, joy etc. We average out these three different responses and present user a score which lies between "More critical" and "Less critical" range of anxious meter.

## Prerequisite
- Register for an [IBM Cloud](https://www.ibm.com/account/reg/us-en/signup?formid=urx-42793&eventid=cfc-2020?cm_mmc=OSocial_Blog-_-Audience+Developer_Developer+Conversation-_-WW_WW-_-cfc-2020-ghub-starterkit-education_ov75914&cm_mmca1=000039JL&cm_mmca2=10008917) account. 
- Require Node installed [Node](https://nodejs.org/en/download/)

## Technology
- IBM Technology
  - [Watson Speech to Text](https://www.ibm.com/cloud/watson-speech-to-text)
  - [Watson Tone Analyzer](https://www.ibm.com/watson/services/tone-analyzer/)
- Open source technologies
  - [Nodejs Express App](https://expressjs.com/)

## Setup

### To get server up and runnig
- Go to the root folder
- run command `npm install`
- run the script `npm run start`



## Documents
