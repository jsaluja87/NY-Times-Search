# Project 2 - *NY Times Search*

**NY Times Search** is an android app that allows a user to search for articles on web using simple filters. The app utilizes [New York Times Search API](http://developer.nytimes.com/docs/read/article_search_api_v2).

Time spent: **20** hours spent in total

## User Stories

The following **required** functionality is completed:

* [Y] User can **search for news article** by specifying a query and launching a search. Search displays a grid of image results from the New York Times Search API.
* [Y] User can click on "settings" which allows selection of **advanced search options** to filter results
* [Y] User can configure advanced search filters such as:
  * [Y] Begin Date (using a date picker)
  * [Y] News desk values (Arts, Fashion & Style, Sports)
  * [Y] Sort order (oldest or newest)
* [Y] Subsequent searches have any filters applied to the search results
* [Y] User can tap on any article in results to view the contents in an embedded browser.
* [Y] User can **scroll down to see more articles**. The maximum number of articles is limited by the API search.

The following **optional** features are implemented:

* [Y] Implements robust error handling, [check if internet is available](http://guides.codepath.com/android/Sending-and-Managing-Network-Requests#checking-for-network-connectivity), handle error cases, network failures
* [Y] Used the **ActionBar SearchView** or custom layout as the query box instead of an EditText
* [Y] User can **share an article link** to their friends or email it to themselves
* [Y] Replaced Filter Settings Activity with a lightweight modal overlay

The following **bonus** features are implemented:

* [Y] Use the [RecyclerView](http://guides.codepath.com/android/Using-the-RecyclerView) with the `StaggeredGridLayoutManager` to display improve the grid of image results
* [Y] For different news articles that only have text or only have images, use [Heterogenous Layouts](http://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView) with RecyclerView
* [N] Use Parcelable instead of Serializable using the popular [Parceler library](http://guides.codepath.com/android/Using-Parceler).
* [Y] Leverages the [data binding support module](http://guides.codepath.com/android/Applying-Data-Binding-for-Views) to bind data into layout templates.
* [Y] Replace all icon drawables and other static image assets with [vector drawables](http://guides.codepath.com/android/Drawables#vector-drawables) where appropriate.
* [Y] Replace Picasso with [Glide](http://inthecheesefactory.com/blog/get-to-know-glide-recommended-by-google/en) for more efficient image rendering.
* [Y] Uses [retrolambda expressions](http://guides.codepath.com/android/Lambda-Expressions) to cleanup event handling blocks.
* [Y] Leverages the popular [GSON library](http://guides.codepath.com/android/Using-Android-Async-Http-Client#decoding-with-gson-library) to streamline the parsing of JSON data.
* [Y] Leverages the [Retrofit networking library](http://guides.codepath.com/android/Consuming-APIs-with-Retrofit) to access the New York Times API.
* [Y] Replace the embedded `WebView` with [Chrome Custom Tabs](http://guides.codepath.com/android/Chrome-Custom-Tabs) using a custom action button for sharing. (_**2 points**_)

The following **additional** features are implemented:

* [Y] List anything else that you can get done to improve the app functionality!
* Added support to make sure that the view items are not lost from orientation change
* Added android ripple effect to view items
* Added splash screen with a gradient
* Added application icon
* Added Toast telling the user to search for interesting articles
* Added Toast notifying the user if nothing returned from NY times API for their query
* Added Dialog box to notify the user about no internet connectivity
* Added Dialog box to notify the user about no network connection
* Added more values to view items
* Added support to make sure that the view items don't reshuffle on scrolling up


## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/jsaluja87/NY-Times-Search/blob/master/Codepath_Assignment2_NY_Times_Search.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

* After replacing webView with Chrome View, there was no place to use Parceler because nothing needed to be passed to activities. So did not implement it

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android

## License

    Copyright [2017] [Jaspreet Saluja]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
