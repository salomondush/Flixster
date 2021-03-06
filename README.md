*Flixster* shows the latest movies currently playing in theaters. The app utilizes the Movie Database API to display images and basic information about these movies to the user.

## SETUP
#You'll need to configure your api key and secrets:
- Create a new folder apikey.properties
- create an entry using the `API_KEY` variable constant: your api key variable should be sorrounded by double quotes

## User Stories

The following *required* functionality is completed:

* [x] User can *scroll through current movies* from the Movie Database API
* [x] Display a nice default [placeholder graphic](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#advanced-usage) for each image during loading
* [x] For each movie displayed, user can see the following details:
  * [x] Title, Poster Image, Overview (Portrait mode)
  * [x] Title, Backdrop Image, Overview (Landscape mode)
* [x] Allow user to view details of the movie including ratings within a separate activity

The following *stretch* features are implemented:

* [x] Improved the user interface by experimenting with styling and coloring.
- Added date strings for both movie view and movie details view with orientation support
- Added poster view on the movie details page
- Added some padding and margin for a better separation and view
- Added corresponding render for landscape and portrait for the movie details view (was not required)
* [x] Apply rounded corners for the poster or background images using [Glide transformations](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#transformations)
* [x] Apply the popular [View Binding annotation library](http://guides.codepath.org/android/Reducing-View-Boilerplate-with-ViewBinding) to reduce boilerplate code.
* [ ] Allow video trailers to be played in full-screen using the YouTubePlayerView from the details screen.

The following *additional* features are implemented:

* [ ] List anything else that you can get done to improve the app functionality!
- More navigation options
- Search bar for movies
- An option to view reviews


## Notes

First time using android studio, so the whole flow of things was really confusing. There's still so much to learn, but I am very excited.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright [2022] [FBU]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
