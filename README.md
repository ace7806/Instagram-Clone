# Project 3 - *Instagram-Clone*

**Instagram-Clone** is a photo sharing app similar to Instagram but using Parse as its backend.

Time spent: **16** hours spent in total

## User Stories

The following **required** functionality is completed:

- [X] User can sign up to create a new account using Parse authentication.
- [X] User can log in and log out of his or her account.
- [X] The current signed in user is persisted across app restarts.
- [X] User can take a photo, add a caption, and post it to "Instagram".

The following **optional** features are implemented:

- [X] User sees app icon in home screen and styled bottom navigation view
- [X] Style the feed to look like the real Instagram feed.
- [X] After the user submits a new post, show an indeterminate progress bar while the post is being uploaded to Parse.

The following **additional** features are implemented:

- [X] Compress submitted photos down as much as 30 to 60 kb for faster uploading/downloading speeds as well as conserving file storage for the database
- [X] Added safety measures to make sure users dont upload the same photo twice by accident
- [X] Incorporated a Bottom Navigation Bar with fragments for navigating faster inside the app 

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://media.giphy.com/media/D1oOxy35559cVxxRRO/giphy.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />


## Notes

Some of the note worthy and fun challenges i faced while making this app was finding the sweet spot where I can compress the photo just enough to
look good and make the server happy that there arent any big requests.

I also noticed when you doule clicked on a button you can accidently sumbit a request twice if there isnt anything stopping you from doing so, so decided to make my own
little switch that desactivates buttons until they are ready to be pressed again

So far the only big challenge is creating and managing icons and drawables. My gosh they are so annoying when they dont come out the way you want them too. I still need to tweek
some images due to them not looking that good in the app bar.

## Open-source libraries used

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright [yyyy] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
