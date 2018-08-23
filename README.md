# AndroidExercise

<b>Project description:</b>

This app is designed to fetch JSON data from a URL (https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json) and dispaly the respone in a list.

The response includes a title and list of rows, each row consists of a title, description and an image. However, it can be null at some rows.

<b>Technical aspects:</b>

Used MVP architecture

Retrofit2 for REST services and caching responses

Butterknife for dependency injection

Glide library for image loading

RecyclerView for displaying rows on the UI

Espresso for Instrumentation UI test.



