# SENSUM | Mental Wellbeing

Improve mental wellbeing by leveraging technology to diagnose for group based solutions.

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
There is a mismatch between the supply and demand in the mental health space with **4000 mental health professionals for ~100 Mn people who suffer from mental health problems** in India as per a WHO report. Mental wellbeing is being further impacted due to the ongoing pandemic and lockdown aggravating the gap between demand and supply

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

### Video transcription/translation app
![Video transcription/translation app](https://github.com/skathuria29/shield-maidens-mh/blob/master/Screenshot%202020-06-06%20at%202.22.32%20PM.png)
//Todo attach running app gif instead of the above image
1. The user navigates to the site and uploads a video file.
2. Watson Speech to Text processes the audio and extracts the text.
3. Watson Translation (optionally) can translate the text to the desired language.
4. The app stores the translated text as a document within Object Storage.

### Android app
<img src="https://github.com/skathuria29/shield-maidens-mh/blob/master/Screenshot_20200606-180414.png" width=250> <img src="https://github.com/skathuria29/shield-maidens-mh/blob/master/Screenshot_20200606-175331.png" width=250> <img src="https://github.com/skathuria29/shield-maidens-mh/blob/master/Screenshot_20200606-175543.png" width=250>

<img src="https://github.com/skathuria29/shield-maidens-mh/blob/master/videogif.gif" width=250>

//Todo attach running app gif instead of the above image
1. The user navigates to the site and uploads a video file.
2. Watson Speech to Text processes the audio and extracts the text.
3. Watson Translation (optionally) can translate the text to the desired language.
4. The app stores the translated text as a document within Object Storage.

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
